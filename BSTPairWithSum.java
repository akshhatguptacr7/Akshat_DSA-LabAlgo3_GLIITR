package FindPair;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int item) {
        val = item;
        left = right = null;
    }
}

public class BSTPairWithSum {
    TreeNode root;

    public boolean findPair(TreeNode root, int sum) {
        if (root == null)
            return false;

        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        TreeNode leftCurr = root;
        TreeNode rightCurr = root;

        while (true) {
            while (leftCurr != null) {
                leftStack.push(leftCurr);
                leftCurr = leftCurr.left;
            }
            while (rightCurr != null) {
                rightStack.push(rightCurr);
                rightCurr = rightCurr.right;
            }

            if (leftStack.isEmpty() || rightStack.isEmpty())
                break;

            TreeNode leftTop = leftStack.peek();
            TreeNode rightTop = rightStack.peek();

            if (leftTop.val >= rightTop.val)
                break;

            int currentSum = leftTop.val + rightTop.val;
            if (currentSum == sum) {
                System.out.println("Pair found: (" + leftTop.val + ", " + rightTop.val + ")");
                return true;
            } else if (currentSum < sum) {
                leftCurr = leftStack.pop().right;
            } else {
                rightCurr = rightStack.pop().left;
            }
        }
        System.out.println("Nodes with the given sum are not found.");
        return false;
    }

    public static void main(String[] args) {
        BSTPairWithSum tree = new BSTPairWithSum();
        tree.root = new TreeNode(60);
        tree.root.left = new TreeNode(50);
        tree.root.right = new TreeNode(70);
        tree.root.left.left = new TreeNode(40);
        tree.root.left.right = new TreeNode(55);
        tree.root.right.right = new TreeNode(80);

        int sum = 130;
        if (!tree.findPair(tree.root, sum)) {
            System.out.println("Nodes with the sum " + sum + " are not found.");
        }
    }
}
