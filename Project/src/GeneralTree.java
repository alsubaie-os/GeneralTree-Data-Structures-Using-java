import java.util.*;

public class GeneralTree<T extends Comparable<? super T>> {

    GeneralTreeNode<T> root = null;

    public GeneralTree() {
    }

    public GeneralTree(GeneralTreeNode rootNode) {
        if (root == null)
            root = new GeneralTreeNode(rootNode);
        else
            System.out.println("Root is not empty, use insert method");
    }

    public void insert(GeneralTreeNode parentNode, GeneralTreeNode newNode) {
        if (isEmpty()) {
            root = parentNode;
            root.addChild(newNode);
        } else if (root.children.isEmpty() && root.info.equals(parentNode.info)) {
            root.addChild(newNode);
        } else {
            find(root, parentNode).addChild(newNode);
        }
    }

    public void delete(GeneralTreeNode existingNode) {
        if (isEmpty()) {
            System.out.println("The tree is empty !");
        } else if (find(root, existingNode) != null) {
            if (root.info.equals(existingNode.info)) {
                root.children.clear();
                root = null;
            } else {
                GeneralTreeNode tmp = find(root, new GeneralTreeNode(existingNode.toString()));
                tmp.parent.children.remove(tmp);
            }
        } else System.out.println("The node you want to delete does not exist !");

    }

    public String search(GeneralTreeNode newNode) {
        if (isEmpty()) {
            System.out.println("The tree is empty !");
        } else if (find(root, newNode) != null) {
            if (root.info.equals(newNode.info))
                return newNode.toString();
            else {
                ArrayList arraylistPath = new ArrayList();
                GeneralTreeNode tmp = find(root, newNode);
                while (tmp != null) {
                    arraylistPath.add(tmp);
                    tmp = tmp.parent;
                }
                Collections.reverse(arraylistPath);
                return arraylistPath.toString().replace("[", "").replace("]", "");
            }
        }
        return "The node you want to search for does not exist !";
    }


    public void sortByLevel() {
        sortByLevel(root);
    }

    private void sortByLevel(GeneralTreeNode root) {
        if (isEmpty())
            return;
        Collections.sort(root.children);
        for (Object child : root.children) {
            if (!((GeneralTreeNode) child).children.isEmpty()) {
                sortByLevel((GeneralTreeNode) child);
            }
        }
    }

    public GeneralTreeNode find(GeneralTreeNode tmp, GeneralTreeNode findNode) {
        if (tmp.info.equals(findNode.info))
            return tmp;
        else {
            for (Object child : tmp.children) {
                GeneralTreeNode generalTreeNode = find((GeneralTreeNode) child, findNode);
                if (generalTreeNode != null)
                    return generalTreeNode;
            }
        }
        return null;
    }

    public ArrayList<T> preOrder() {
        ArrayList treeList = new ArrayList();
        return preOrder(root, treeList);
    }

    private ArrayList<T> preOrder(GeneralTreeNode root, ArrayList list) {
        if (isEmpty())
            return list;
        list.add(root.info);
        for (Object child : root.children) {
            preOrder((GeneralTreeNode) child, list);
        }
        return list;
    }

    public ArrayList<T> postOrder() {
        ArrayList treeList = new ArrayList();
        return postOrder(root, treeList);
    }

    private ArrayList<T> postOrder(GeneralTreeNode root, ArrayList list) {
        if (isEmpty())
            return list;
        for (Object child : root.children) {
            postOrder((GeneralTreeNode) child, list);
        }
        list.add(root.info);
        return list;
    }

    public void bfs(GeneralTreeNode root) {
        if (root == null)
            return;

        Queue<GeneralTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {

                GeneralTreeNode generalTreeNode = queue.peek();
                queue.remove();
                System.out.print(generalTreeNode.info + " ");


                for (int i = 0; i < generalTreeNode.children.size(); i++)
                    queue.add((GeneralTreeNode) generalTreeNode.children.get(i));
                size--;
            }
            System.out.println();
        }
    }


    public boolean isEmpty() {
        return root == null;
    }

    public void numberOfFilesAndFolders(GeneralTreeNode tmp) {

        int folders = 0;
        int files = 0;

        if (!root.children.isEmpty()) {
            folders++;
            for (Object child : tmp.children) {
                if (!((GeneralTreeNode) child).children.isEmpty())
                    folders++;
                else
                    files++;
                for (Object childOfChild : ((GeneralTreeNode) child).children) {
                    files++;
                }
            }
        } else
            files++;
        System.out.println("The number of folders : " + folders + "\nThe number of files :" + files);
    }

}

