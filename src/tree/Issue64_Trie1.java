package tree;

/**
 * 实现一个前缀树
 * 子节点使用数组方式
 */
public class Issue64_Trie1 {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("abd");
        trie.insert("acd");
        trie.insert("abc");
        System.out.println("abc有多少个：" + trie.search("abc"));
        System.out.println("ab开始的字符串有多少个：" + trie.prefixCount("ab"));
        trie.delete("abc");
        System.out.println("删除一个abc后，还有多少个abc：" + trie.search("abc"));
    }

    // 这里使用数组存子节点
    static class TrieNode {
        private final TrieNode[] children;
        private int pass;
        private int end;

        public TrieNode() {
            pass = 0;
            end = 0;
            // 这里假设字符串都是小写字母，总共就26个
            children = new TrieNode[26];
        }
    }

    static class Trie {
        private final TrieNode root;

        private Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            for (char c : str) {
                int path = c - 'a';
                if (node.children[path] == null) {
                    node.children[path] = new TrieNode();
                }
                node = node.children[path];
                node.pass++;
            }
            node.end++;
        }

        void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] str = word.toCharArray();
            TrieNode node = root;
            node.pass--;
            for (char c : str) {
                int path = c - 'a';
                // 防止内存泄露
                if (--node.children[path].pass == 0) {
                    node.children[path] = null;
                    return;
                }
                node = node.children[path];
            }
            node.end--;
        }

        // 查询字符串存在几个
        int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] str = word.toCharArray();
            TrieNode node = root;
            for (char c : str) {
                int path = c - 'a';
                if (node.children[path] == null) {
                    return 0;
                }
                node = node.children[path];
            }
            return node.end;
        }

        // 查询有多少个字符串是以prefix前缀的
        int prefixCount(String prefix) {
            if (prefix == null) {
                return 0;
            }
            char[] str = prefix.toCharArray();
            TrieNode node = root;
            for (char c : str) {
                int path = c - 'a';
                if (node.children[path] == null) {
                    return 0;
                }
                node = node.children[path];
            }
            return node.pass;
        }
    }
}
