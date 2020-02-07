package com.wyx.suanfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @auth: wangyx-p
 * @date :2019/11/20 15:59
 * @Description:
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class NoRepeatString {

    /**
     *
     * 假设我们有一个函数 boolean allUnique(String substring) ，如果子字符串中的字符都是唯一的，它会返回 true，否则会返回 false。 我们可以遍历给定字符串 s 的所有可能的子字符串并调用函数 allUnique。 如果事实证明返回值为 true，那么我们将会更新无重复字符子串的最大长度的答案。
     *
     * 现在让我们填补缺少的部分：
     *
     *     为了枚举给定字符串的所有子字符串，我们需要枚举它们开始和结束的索引。假设开始和结束的索引分别为 iii 和 jjj。那么我们有 0≤i<j≤n0 \leq i \lt j \leq n0≤i<j≤n（这里的结束索引 jjj 是按惯例排除的）。因此，使用 iii 从 0 到 n−1n - 1n−1 以及 jjj 从 i+1i+1i+1 到 nnn 这两个嵌套的循环，我们可以枚举出 s 的所有子字符串。
     *
     *     要检查一个字符串是否有重复字符，我们可以使用集合。我们遍历字符串中的所有字符，并将它们逐个放入 set 中。在放置一个字符之前，我们检查该集合是否已经包含它。如果包含，我们会返回 false。循环结束后，我们返回 true。
     * 时间复杂度：O(n3)O(n^3)O(n3) 。
     * 空间复杂度：O(min(n,m))O(min(n, m))O(min(n,m))，我们需要 O(k)O(k)O(k) 的空间来检查子字符串中是否有重复字符，其中 kkk 表示 Set 的大小。而 Set 的大小取决于字符串 nnn 的大小以及字符集/字母 mmm 的大小。
     *

     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
    /**
    * 在暴力法中，我们会反复检查一个子字符串是否含有有重复的字符，但这是没有必要的。如果从索引 iii 到 j−1j - 1j−1 之间的子字符串 sijs_{ij}sij​ 已经被检查为没有重复字符。我们只需要检查 s[j]s[j]s[j] 对应的字符是否已经存在于子字符串 sijs_{ij}sij​ 中。

要检查一个字符是否已经在子字符串中，我们可以检查整个子字符串，这将产生一个复杂度为 O(n2)O(n^2)O(n2) 的算法，但我们可以做得更好。

通过使用 HashSet 作为滑动窗口，我们可以用 O(1)O(1)O(1) 的时间来完成对字符是否在当前的子字符串中的检查。

滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i,j)[i, j)[i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i,j)[i, j)[i,j) 向右滑动 111 个元素，则它将变为 [i+1,j+1)[i+1, j+1)[i+1,j+1)（左闭，右开）。

回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i,j)[i, j)[i,j)（最初 j=ij = ij=i）中。 然后我们向右侧滑动索引 jjj，如果它不在 HashSet 中，我们会继续滑动 jjj。直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 iii 开头。如果我们对所有的 iii 这样做，就可以得到答案。
     时间复杂度：O(2n)=O(n)O(2n) = O(n)O(2n)=O(n)，在最糟糕的情况下，每个字符将被 iii 和 jjj 访问两次。

     空间复杂度：O(min(m,n))O(min(m, n))O(min(m,n))，与之前的方法相同。滑动窗口法需要 O(k)O(k)O(k) 的空间，其中 kkk 表示 Set 的大小。而 Set 的大小取决于字符串 nnn 的大小以及字符集 / 字母 mmm 的大小。

     * */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。 当我们找到重复的字符时，我们可以立即跳过该窗口。
     *
     * 也就是说，如果 s[j]s[j]s[j] 在 [i,j)[i, j)[i,j) 范围内有与 j′j'j′ 重复的字符，我们不需要逐渐增加 iii 。 我们可以直接跳过 [i，j′][i，j'][i，j′] 范围内的所有元素，并将 iii 变为 j′+1j' + 1j′+1。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


    /**
     * 以前的我们都没有对字符串 s 所使用的字符集进行假设。
     *
     * 当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。
     *
     * 常用的表如下所示：
     *
     *     int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
     *     int [128] 用于ASCII码
     *     int [256] 用于扩展ASCII码
     *

     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("ad1fa2df"));
        System.out.println(lengthOfLongestSubstring2("ad1fa2df"));
    }

}