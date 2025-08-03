import java.util.*;

public class Solution {
    /**
     * 找出能产生最多不同hash值的secretKey
     * 
     * 算法思路：
     * 1. 对于每个param[i]，hash[i] = secretKey[i] % param[i]的值域是[0, param[i]-1]
     * 2. 为了最大化不同的hash值，我们需要让每个hash[i]尽可能不同
     * 3. 如果两个参数param[i]和param[j]相同，那么它们最多只能贡献1个不同的hash值
     * 4. 因此，答案是param数组中不同值的个数
     * 
     * @param param 参数数组
     * @return 最大可能的不同hash值数量
     */
    public static int findHash(int[] param) {
        // 使用HashSet来统计param数组中不同值的个数
        Set<Integer> uniqueParams = new HashSet<>();
        
        // 将所有参数值加入set中，自动去重
        for (int p : param) {
            uniqueParams.add(p);
        }
        
        // 返回不同参数值的个数
        return uniqueParams.size();
    }
    
    /**
     * 验证函数：给定param和secretKey，计算实际的不同hash值数量
     * 用于测试我们的解法是否正确
     */
    public static int calculateDistinctHashes(int[] param, int[] secretKey) {
        Set<Integer> hashes = new HashSet<>();
        for (int i = 0; i < param.length; i++) {
            hashes.add(secretKey[i] % param[i]);
        }
        return hashes.size();
    }
    
    public static void main(String[] args) {
        // 测试用例1：题目中的例子
        int[] param1 = {1, 2, 4};
        System.out.println("Test 1:");
        System.out.println("param = " + Arrays.toString(param1));
        System.out.println("Maximum distinct hash values: " + findHash(param1));
        
        // 验证：使用secretKey = [1, 3, 2]
        int[] secretKey1 = {1, 3, 2};
        System.out.println("Using secretKey = " + Arrays.toString(secretKey1));
        System.out.println("Actual distinct values: " + calculateDistinctHashes(param1, secretKey1));
        System.out.println();
        
        // 测试用例2：有重复参数的情况
        int[] param2 = {3, 3, 5, 7, 5};
        System.out.println("Test 2:");
        System.out.println("param = " + Arrays.toString(param2));
        System.out.println("Maximum distinct hash values: " + findHash(param2));
        
        // 测试用例3：所有参数都相同
        int[] param3 = {4, 4, 4, 4};
        System.out.println("\nTest 3:");
        System.out.println("param = " + Arrays.toString(param3));
        System.out.println("Maximum distinct hash values: " + findHash(param3));
        
        // 测试用例4：所有参数都不同
        int[] param4 = {2, 3, 5, 7, 11};
        System.out.println("\nTest 4:");
        System.out.println("param = " + Arrays.toString(param4));
        System.out.println("Maximum distinct hash values: " + findHash(param4));
    }
}

/**
 * 算法正确性证明：
 * 
 * 1. 对于相同的param[i]值，无论选择什么secretKey[i]，它们的hash值范围都是[0, param[i]-1]
 * 2. 如果有k个位置的param值都等于p，那么这k个位置最多只能产生p个不同的hash值
 * 3. 但是由于我们要让整个hash数组的不同值尽可能多，对于这k个相同的param值，
 *    我们最多只能让它们产生1个相同的hash值（否则会浪费可能的不同值）
 * 4. 因此，最优策略是：对于每个不同的param值，贡献1个不同的hash值
 * 5. 所以答案就是param数组中不同值的个数
 * 
 * 时间复杂度：O(n)，其中n是param数组的长度
 * 空间复杂度：O(k)，其中k是param数组中不同值的个数
 */