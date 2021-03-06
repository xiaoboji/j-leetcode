- leetcode链接

[105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- 解题思路

对于任意一颗树而言，前序遍历的形式总是

[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]

即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是

[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]

只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。

```
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int prelen = preorder.length;
        int inlen = inorder.length;
        if (prelen != inlen) {
            throw new RuntimeException("Incorrect input data. ");
        }
        // 缓存中序遍历，O(1)快速找到节点位置
        Map<Integer, Integer> map = new HashMap<>(prelen);
        for (int i = 0; i < inlen ; i++) {
            map.put(inorder[i],i);
        }
        return buildTree(preorder,0,prelen - 1,map,0,inlen -1);
    }

  /**
   * Build tree tree node.
   *
   * @param preorder the preorder
   * @param preLeft the pre left 前序遍历左边界
   * @param preRight the pre right 前序遍历由边界
   * @param map the map 缓存，方便找到中序遍历的节点的位置
   * @param inLeft the in left 中序遍历左边界
   * @param inRight the in right 中序遍历右边界
   * @return the tree node
   */
  public TreeNode buildTree(int[] preorder, int preLeft, int preRight, Map<Integer,Integer> map, int inLeft, int inRight) {
      if (preLeft > preRight || inLeft > inRight) {
          return null;
      }
      int rootVal = preorder[preLeft];
      TreeNode root = new TreeNode(rootVal);
      // 前序遍历的第一节点在中序遍历中的节点的下标
      int pIndex = map.get(rootVal);
      root.left = buildTree(preorder,preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex -1);
      root.right = buildTree(preorder, pIndex - inLeft + preLeft + 1, preRight, map ,pIndex + 1, inRight);
      return root;
  }
}
```
时间复杂度：O(n)，其中 n 是树中的节点个数。

空间复杂度：O(n)，除去返回的答案需要的 O(n) 空间之外，我们还需要使用 O(n) 的空间存储哈希映射，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。这里 h < nh<n，所以总空间复杂度为 O(n)O(n)。