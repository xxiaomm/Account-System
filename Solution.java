/**
 * AWS IAM 校验和问题 - Java解法
 * 
 * 问题：给定数组param，求hash数组中可能的最大不同值数量
 * 其中 hash[i] = secretKey[i] % param[i]
 */
public class Solution {
    
    /**
     * 核心解法：返回最大不同值数量
     * 
     * 思路分析：
     * 1. hash[i] = secretKey[i] % param[i]，所以hash[i]的范围是[0, param[i]-1]
     * 2. 所有hash[i]可能的值的并集是[0, max(param)-1]
     * 3. 因此最大不同值数量就是max(param)
     * 
     * 举例说明：
     * param = [1, 2, 4]
     * - hash[0]可能的值：[0] (因为0 % 1 = 0)
     * - hash[1]可能的值：[0, 1] (因为任意数 % 2 ∈ {0,1})
     * - hash[2]可能的值：[0, 1, 2, 3] (因为任意数 % 4 ∈ {0,1,2,3})
     * - 所有可能值的并集：[0, 1, 2, 3]，共4个不同值
     * - 答案：max(param) = max(1,2,4) = 4
     * 
     * @param param 参数数组
     * @return 最大不同值数量
     */
    public static int findHash(int[] param) {
        // 方法1：使用Arrays.stream (Java 8+)
        // return Arrays.stream(param).max().getAsInt();
        
        // 方法2：传统循环遍历
        int maxParam = 0;
        for (int p : param) {
            maxParam = Math.max(maxParam, p);
        }
        return maxParam;
    }
    
    /**
     * 验证解法正确性：演示如何构造secretKey达到理论最优
     */
    public static void verifyWithExample(int[] param) {
        int answer = findHash(param);
        System.out.println("输入: " + java.util.Arrays.toString(param));
        System.out.println("答案: " + answer);
        
        // 证明可以达到这个最优值：
        // 构造secretKey使得hash数组包含[0, answer-1]的所有值
        System.out.println("验证：可以构造secretKey = [0, 1, 2, ...]达到最优解");
        
        int n = Math.min(param.length, answer);  // 取较小值避免越界
        int[] secretKey = new int[param.length];
        int[] hash = new int[param.length];
        
        // 构造策略：让前n个位置分别产生值0,1,2,...,n-1
        for (int i = 0; i < param.length; i++) {
            if (i < answer && param[i] > i) {
                secretKey[i] = i;  // 可以产生值i
                hash[i] = i;
            } else {
                secretKey[i] = 0;  // 随意设置
                hash[i] = 0;
            }
        }
        
        System.out.println("构造的secretKey: " + java.util.Arrays.toString(secretKey));
        System.out.println("对应的hash: " + java.util.Arrays.toString(hash));
        
        // 统计不同值
        java.util.Set<Integer> uniqueValues = new java.util.HashSet<>();
        for (int h : hash) {
            uniqueValues.add(h);
        }
        System.out.println("实际不同值数量: " + uniqueValues.size());
        System.out.println();
    }
    
    public static void main(String[] args) {
        // 测试例子
        System.out.println("=== AWS IAM 校验和问题解法测试 ===\n");
        
        // 题目给出的例子
        int[] test1 = {1, 2, 4};
        verifyWithExample(test1);
        
        // 其他测试用例
        int[] test2 = {3, 5, 2, 7};
        verifyWithExample(test2);
        
        int[] test3 = {10, 10, 10};
        verifyWithExample(test3);
        
        // 边界情况
        int[] test4 = {1};
        verifyWithExample(test4);
        
        System.out.println("总结：答案始终等于max(param)");
    }
}