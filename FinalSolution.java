/**
 * AWS IAM 校验和问题的Java解决方案
 * 
 * 问题分析：
 * - hash[i] = secretKey[i] % param[i]
 * - 目标：找到hash数组中可能的最大不同值数量
 * 
 * 核心洞察：
 * 每个hash[i]的可能值范围是[0, param[i]-1]
 * 所有hash值的并集范围是[0, max(param)-1]
 * 因此最大不同值数量就是max(param)
 */
public class FinalSolution {
    
    /**
     * 求解函数：返回hash数组中可能的最大不同值数量
     * 
     * @param param 参数数组
     * @return 最大不同值数量
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int findHash(int[] param) {
        // 找到param数组中的最大值
        int maxParam = 0;
        for (int p : param) {
            maxParam = Math.max(maxParam, p);
        }
        
        // 最大不同值数量等于最大的param值
        // 因为hash[i] = secretKey[i] % param[i]的取值范围是[0, param[i]-1]
        // 所有可能的hash值范围是[0, maxParam-1]，共maxParam个不同值
        return maxParam;
    }
    
    /**
     * 演示如何构造secretKey达到最优解
     */
    public static void demonstrateOptimalConstruction(int[] param) {
        int n = param.length;
        int maxParam = 0;
        
        // 找到最大值
        for (int p : param) {
            maxParam = Math.max(maxParam, p);
        }
        
        System.out.println("输入参数: " + java.util.Arrays.toString(param));
        System.out.println("最大不同值数量: " + maxParam);
        System.out.println();
        
        // 分析：为了得到maxParam个不同值，我们需要hash数组包含[0, maxParam-1]的所有值
        System.out.println("目标：让hash数组包含[0, " + (maxParam-1) + "]的所有值");
        
        // 构造策略：
        // 对于每个目标值v，如果存在某个位置i使得param[i] > v，
        // 那么我们可以设置secretKey[i] = v，从而得到hash[i] = v
        
        java.util.Set<Integer> achievableValues = new java.util.HashSet<>();
        
        for (int targetValue = 0; targetValue < maxParam; targetValue++) {
            boolean canAchieve = false;
            for (int i = 0; i < n; i++) {
                if (param[i] > targetValue) {
                    canAchieve = true;
                    System.out.println("值 " + targetValue + " 可以通过位置 " + i + " 实现 (param[" + i + "] = " + param[i] + " > " + targetValue + ")");
                    achievableValues.add(targetValue);
                    break;
                }
            }
            if (!canAchieve) {
                System.out.println("值 " + targetValue + " 无法实现 (所有param[i] <= " + targetValue + ")");
            }
        }
        
        System.out.println("\n可实现的值的数量: " + achievableValues.size());
        System.out.println("理论最大值: " + maxParam);
        
        // 实际构造一个secretKey示例
        int[] secretKey = new int[n];
        int[] hash = new int[n];
        boolean[] used = new boolean[n];
        java.util.Set<Integer> actualValues = new java.util.HashSet<>();
        
        // 贪心构造：按顺序尝试获得0到maxParam-1的每个值
        for (int targetValue = 0; targetValue < maxParam; targetValue++) {
            for (int i = 0; i < n; i++) {
                if (!used[i] && param[i] > targetValue) {
                    secretKey[i] = targetValue;
                    hash[i] = targetValue;
                    actualValues.add(targetValue);
                    used[i] = true;
                    break;
                }
            }
        }
        
        // 对剩余位置设置任意值
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                secretKey[i] = 0;  // 任意值
                hash[i] = secretKey[i] % param[i];
                actualValues.add(hash[i]);
            }
        }
        
        System.out.println("\n构造的secretKey: " + java.util.Arrays.toString(secretKey));
        System.out.println("得到的hash数组: " + java.util.Arrays.toString(hash));
        System.out.println("实际不同值数量: " + actualValues.size());
        System.out.println("不同的值: " + actualValues);
        
        if (actualValues.size() == maxParam) {
            System.out.println("✓ 成功达到理论最优！");
        } else {
            System.out.println("× 未完全达到理论最优");
        }
    }
    
    public static void main(String[] args) {
        // 测试题目中的例子
        System.out.println("=== 测试用例1：题目例子 ===");
        int[] param1 = {1, 2, 4};
        System.out.println("答案: " + findHash(param1));
        demonstrateOptimalConstruction(param1);
        
        System.out.println("\n=== 测试用例2 ===");
        int[] param2 = {3, 5, 2, 7};
        System.out.println("答案: " + findHash(param2));
        demonstrateOptimalConstruction(param2);
        
        System.out.println("\n=== 测试用例3 ===");
        int[] param3 = {10, 10, 10};
        System.out.println("答案: " + findHash(param3));
        demonstrateOptimalConstruction(param3);
        
        System.out.println("\n=== 边界测试 ===");
        int[] param4 = {1};
        System.out.println("单个元素[1]: " + findHash(param4));
        
        int[] param5 = {1000000};
        System.out.println("大数值[1000000]: " + findHash(param5));
    }
}