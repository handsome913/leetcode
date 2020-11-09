package leetcode127;

import java.util.*;

/**
 * 本题可以抽象成，求一个点到另一个点的最短路径，自然我们就想到了图的搜索。
 * 图的搜索有两种：深度优先搜索（DFS），广度优先搜索(BFS)。
 *
 * 深度优先搜索从某个顶点出发，首先访问这个顶点，然后找出刚访问这个结点的第一个未被访问的邻结点，
 * 然后再以此邻结点为顶点，继续找它的下一个新的顶点进行访问，重复此步骤，直到所有结点都被访问完为止；
 *
 * 广度优先遍历从某个顶点出发，首先访问这个顶点，然后找出这个结点的所有未被访问的邻接点，
 * 访问完后再访问这些结点中第一个邻接点的所有结点，重复此方法，直到所有结点都被访问完为止。
 *
 * 我们采用广度优先搜索的方法，因为（DijKstra 算法）求最短路径就是采用的广度优先搜索的思想。
 *
 * 下面我们还要解决的是：一个点到另一点是否可达，即当前word变换一个字母后，能否与剩余未访问的某个word相同。
 * */
public class Solution{
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //1. 先将wordList放到Hash表中，便于判断某个单词是否在wordList里。
        Set<String> wordSet = new HashSet<>(wordList);
        if(wordSet.size()==0|| !wordSet.contains(endWord))
            return 0;
        wordSet.remove(beginWord);
        //2. 图的广度优先遍历，必须使用队列和表示访问过的visited哈希表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        //3. 开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        int step = 1;
        while(!queue.isEmpty()){
            int currentSize = queue.size();
            for(int i =0; i<currentSize; i++) {
                //依次遍历当前队列中的单词
                String currentWord = queue.poll();
                //如果 currentWord 能够修改1个字符与endWord相同，则返回 step+1;
                if(changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)) {
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }
    /**
     * 尝试对 currentWord 修改每一个字符，看看是不是能与endWord匹配。
     * */
    private boolean changeWordEveryOneLetter(String currentWord, String endWord,
                                             Queue<String> queue, Set<String> visited, Set<String> wordSet) {
        char[] charArray = currentWord.toCharArray();
        for(int i=0; i< endWord.length(); i++){
            //先保存然后恢复
            char originChar = charArray[i];
            for(char c='a'; c <='z'; c++){
                if(c==originChar) continue;
                charArray[i]=c;
                String nextWord= String.valueOf(charArray);
                if(wordSet.contains(nextWord)){
                    if(nextWord.equals(endWord)) return true;
                    if(!visited.contains(nextWord)){
                        queue.offer(nextWord);
                        //紧接着要标记为已访问
                        visited.add(nextWord);
                    }
                }

            }
            //恢复
            charArray[i] = originChar;
        }
        return false;
    }
}