public class FindHash {
    
    /**
     * 找到hash数组中可能的最大不同值数量
     * 
     * 问题分析：
     * - hash[i] = secretKey[i] % param[i]
     * - hash[i] 的可能值范围是 [0, param[i]-1]
     * - 对于每个位置i，hash[i] 最多有 param[i] 种不同的值
     * - 我们需要选择secretKey使得整个hash数组中不同值的总数最大化
     * 
     * 关键洞察：
     * 1. 对于每个位置i，hash[i]可以取 [0, param[i]-1] 范围内的任意值
     * 2. 我们可以通过选择合适的secretKey[i]来控制hash[i]的值
     * 3. 为了最大化不同值的数量，我们应该尽量让不同位置的hash值不重复
     * 
     * @param param 参数数组
     * @return hash数组中可能的最大不同值数量
     */
    public static int findHash(int[] param) {
        int n = param.length;
        
        // 收集所有可能的hash值并去重
        // 使用HashSet来自动处理重复值
        java.util.Set<Integer> possibleValues = new java.util.HashSet<>();
        
        // 对于每个位置i，hash[i]可以取值 [0, param[i]-1]
        for (int i = 0; i < n; i++) {
            for (int value = 0; value < param[i]; value++) {
                possibleValues.add(value);
            }
        }
        
        // 返回所有可能的不同值的数量
        return possibleValues.size();
    }
    
    /**
     * 优化版本：直接计算最大值而不使用Set
     * 
     * 更深入的分析：
     * - 每个hash[i]的取值范围是[0, param[i]-1]
     * - 所有可能的不同值就是所有param[i]中最大值减1的范围
     * - 即：[0, max(param)-1]
     * - 因此最大不同值数量就是max(param)
     */
    public static int findHashOptimized(int[] param) {
        int maxParam = 0;
        
        // 找到param数组中的最大值
        for (int p : param) {
            maxParam = Math.max(maxParam, p);
        }
        
        // 最大不同值数量等于最大的param值
        // 因为hash值的范围是[0, maxParam-1]，共maxParam个不同值
        return maxParam;
    }
    
    /**
     * 验证函数：展示如何构造一个实际的secretKey来达到最大不同值
     */
    public static void demonstrateConstruction(int[] param) {
        int n = param.length;
        int maxParam = 0;
        
        // 找到最大的param值
        for (int p : param) {
            maxParam = Math.max(maxParam, p);
        }
        
        System.out.println("参数数组: " + java.util.Arrays.toString(param));
        System.out.println("最大可能的不同值数量: " + maxParam);
        
        // 构造一个更好的secretKey示例来达到最大不同值
        int[] secretKey = new int[n];
        int[] hash = new int[n];
        
        // 改进策略：为了达到最大的不同值数量，我们需要让hash数组
        // 包含尽可能多的 [0, maxParam-1] 范围内的值
        
        // 贪心策略：按顺序分配0到maxParam-1的值
        java.util.Set<Integer> usedValues = new java.util.HashSet<>();
        int targetValue = 0;
        
        for (int i = 0; i < n && targetValue < maxParam; i++) {
            // 对于位置i，我们想要hash[i] = targetValue
            // 只要targetValue < param[i]，我们就可以设置secretKey[i] = targetValue
            if (targetValue < param[i]) {
                secretKey[i] = targetValue;
                hash[i] = secretKey[i] % param[i];
                usedValues.add(hash[i]);
                targetValue++;
            } else {
                // 如果targetValue >= param[i]，我们只能取模
                secretKey[i] = targetValue;
                hash[i] = secretKey[i] % param[i];
                usedValues.add(hash[i]);
            }
        }
        
        // 对于剩余的位置，可以随意设置
        for (int i = 0; i < n; i++) {
            if (secretKey[i] == 0 && i > 0) {  // 未设置的位置
                secretKey[i] = i;  // 随意设置
                hash[i] = secretKey[i] % param[i];
                usedValues.add(hash[i]);
            }
        }
        
        System.out.println("构造的secretKey: " + java.util.Arrays.toString(secretKey));
        System.out.println("对应的hash数组: " + java.util.Arrays.toString(hash));
        System.out.println("实际的不同值数量: " + usedValues.size());
        System.out.println("不同的值: " + usedValues);
        
        // 展示理论最优解
        System.out.println("\n理论分析：");
        System.out.println("- 每个hash[i]的取值范围是[0, param[i]-1]");
        System.out.println("- 所有可能的hash值组合起来的范围是[0, max(param)-1]");
        System.out.println("- 因此最大不同值数量 = max(param) = " + maxParam);
        
        // 展示如何达到理论最优
        System.out.println("\n如何达到最优解：");
        System.out.println("我们需要构造secretKey使得hash数组包含[0, " + (maxParam-1) + "]中的所有值");
        
        // 重新构造以达到最优
        int[] optimalSecretKey = new int[n];
        int[] optimalHash = new int[n];
        java.util.Set<Integer> optimalValues = new java.util.HashSet<>();
        boolean[] positionUsed = new boolean[n];  // 跟踪哪些位置已经使用
        
        // 尝试让hash数组包含0到maxParam-1的所有值
        for (int value = 0; value < maxParam; value++) {
            // 找一个位置i使得param[i] > value，然后设置secretKey[i] = value
            for (int i = 0; i < n; i++) {
                if (param[i] > value && !positionUsed[i]) {
                    optimalSecretKey[i] = value;
                    optimalHash[i] = value;
                    optimalValues.add(value);
                    positionUsed[i] = true;
                    break;
                }
            }
        }
        
        // 对于未使用的位置，随意设置值
        for (int i = 0; i < n; i++) {
            if (!positionUsed[i]) {
                optimalSecretKey[i] = 0;  // 可以是任意值
                optimalHash[i] = optimalSecretKey[i] % param[i];
                optimalValues.add(optimalHash[i]);
            }
        }
        
        System.out.println("最优secretKey示例: " + java.util.Arrays.toString(optimalSecretKey));
        System.out.println("最优hash数组: " + java.util.Arrays.toString(optimalHash));
        System.out.println("最优解的不同值数量: " + optimalValues.size());
        
        // 验证是否真的达到了理论最优
        if (optimalValues.size() == maxParam) {
            System.out.println("✓ 成功达到理论最优值！");
        } else {
            System.out.println("× 未达到理论最优，可能需要更复杂的构造策略");
            System.out.println("理论最优: " + maxParam + ", 实际达到: " + optimalValues.size());
            
            // 解释为什么可能达不到理论最优
            System.out.println("\n分析：为什么可能达不到理论最优？");
            for (int value = 0; value < maxParam; value++) {
                boolean canAchieve = false;
                for (int i = 0; i < n; i++) {
                    if (param[i] > value) {
                        canAchieve = true;
                        break;
                    }
                }
                if (!canAchieve) {
                    System.out.println("- 值 " + value + " 无法实现，因为所有param[i]都 <= " + value);
                }
            }
        }
    }
    
    // 测试方法
    public static void main(String[] args) {
        // 测试用例1：题目中的例子
        int[] param1 = {1, 2, 4};
        System.out.println("=== 测试用例1 ===");
        System.out.println("输入: " + java.util.Arrays.toString(param1));
        System.out.println("结果: " + findHash(param1));
        System.out.println("优化结果: " + findHashOptimized(param1));
        demonstrateConstruction(param1);
        
        System.out.println("\n=== 测试用例2 ===");
        int[] param2 = {3, 5, 2, 7};
        System.out.println("输入: " + java.util.Arrays.toString(param2));
        System.out.println("结果: " + findHash(param2));
        System.out.println("优化结果: " + findHashOptimized(param2));
        demonstrateConstruction(param2);
        
        System.out.println("\n=== 测试用例3 ===");
        int[] param3 = {10, 10, 10};
        System.out.println("输入: " + java.util.Arrays.toString(param3));
        System.out.println("结果: " + findHash(param3));
        System.out.println("优化结果: " + findHashOptimized(param3));
        demonstrateConstruction(param3);
    }
}