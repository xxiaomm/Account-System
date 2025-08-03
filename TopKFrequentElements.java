import java.util.*;

public class TopKFrequentElements {
    
    /**
     * 解法1：使用HashMap + 小顶堆（PriorityQueue）
     * 时间复杂度：O(n log k)，其中 n 是数组长度
     * 空间复杂度：O(n + k)
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 步骤1：统计每个元素的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // 步骤2：使用小顶堆维护前k个最高频率的元素
        // 小顶堆按频率排序，堆顶是频率最小的元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
        );
        
        // 遍历频率映射，维护大小为k的小顶堆
        for (int num : frequencyMap.keySet()) {
            minHeap.offer(num);
            // 如果堆大小超过k，移除频率最小的元素
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // 步骤3：从堆中提取结果
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
    
    /**
     * 解法2：使用HashMap + 桶排序
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        // 步骤1：统计频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // 步骤2：桶排序 - 创建频率桶
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int num : frequencyMap.keySet()) {
            int freq = frequencyMap.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }
        
        // 步骤3：从高频率到低频率收集结果
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                result.addAll(buckets[i]);
            }
        }
        
        // 转换为数组并返回前k个元素
        return result.stream().limit(k).mapToInt(i -> i).toArray();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        
        // 测试用例1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] result1 = solution.topKFrequent(nums1, k1);
        System.out.println("测试用例1结果: " + Arrays.toString(result1)); // 期望: [1, 2] 或 [2, 1]
        
        // 测试用例2
        int[] nums2 = {1};
        int k2 = 1;
        int[] result2 = solution.topKFrequent(nums2, k2);
        System.out.println("测试用例2结果: " + Arrays.toString(result2)); // 期望: [1]
        
        // 使用桶排序方法测试
        int[] result3 = solution.topKFrequentBucketSort(nums1, k1);
        System.out.println("桶排序方法结果: " + Arrays.toString(result3));
        
        // 额外测试用例
        int[] nums3 = {4, 1, -1, 2, -1, 2, 3};
        int k3 = 2;
        int[] result4 = solution.topKFrequent(nums3, k3);
        System.out.println("额外测试用例结果: " + Arrays.toString(result4)); // 期望: [-1, 2] 或 [2, -1]
    }
}