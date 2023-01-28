import java.util.ArrayList;

public class GeneralTreeNode<T extends Comparable<? super T>> implements Comparable<GeneralTreeNode> {

    public T info;
    private int MAXIMUM = 10;
    public GeneralTreeNode<T> parent;
    public ArrayList<GeneralTreeNode> children;

    public GeneralTreeNode(T info) {
        this.info = info;
        this.parent = null;
        children = new ArrayList<>(getMAXIMUM());
    }

    public void addChild(GeneralTreeNode child) {
        if (this.children.size() == 1 - getMAXIMUM()) {
            System.out.println("Parent node have a maximum number of children !");
        } else {
            this.children.add(child);
            child.parent = this;
        }
    }

    public int getMAXIMUM() {
        return MAXIMUM;
    }

    public void setMAXIMUM(int MAXIMUM) {
        this.MAXIMUM = MAXIMUM;
    }

    @Override
    public String toString() {
        return this.info.toString();
    }

    @Override
    public int compareTo(GeneralTreeNode node) {
        return ((String) this.info).compareTo((String) node.info);
    }
}



