import java.io.File;
import java.util.Scanner;

public class TestClass {

    private static GeneralTree generalTree = new GeneralTree();
    private static int num;

    public static void main(String[] args) throws Exception {


        do {
            Scanner scanner = new Scanner(System.in);
            num = getMenuChoice();

            switch (num) {
                case 1: {

                    System.out.println("Enter the path for the tree:");
                    insert(new File(scanner.nextLine()));
                    break;
                }
                case 2: {
                    String folder;
                    String file;
                    System.out.println("Enter the number of files/folder you want to add :");
                    int h = scanner.nextInt();
                    for (int i = 0; i < h; i++) {
                        System.out.println("Enter the folder name for the new file");
                        folder = scanner.next();
                        System.out.println("Enter the new file name");
                        file = scanner.next();
                        generalTree.insert(new GeneralTreeNode(folder), new GeneralTreeNode(file));
                    }
                    break;
                }
                case 3: {
                    if (!generalTree.isEmpty())
                        generalTree.bfs(generalTree.root);
                    else
                        System.out.println("The tree is empty !");
                    break;
                }
                case 4: {
                    if (!generalTree.isEmpty()) {
                        System.out.println("Enter the file/folder name to delete :");
                        generalTree.delete(new GeneralTreeNode(scanner.next()));
                    } else
                        System.out.println("The tree is empty !");
                    break;
                }
                case 5: {
                    if (!generalTree.isEmpty()) {
                        System.out.println("Enter the file/folder name to print it's path :");
                        System.out.println(generalTree.search(new GeneralTreeNode(scanner.next())));
                    } else
                        System.out.println("The tree is empty !");
                    break;
                }
                case 6: {
                    if (!generalTree.isEmpty()) {
                        generalTree.sortByLevel();
                        generalTree.bfs(generalTree.root);
                    } else
                        System.out.println("The tree is empty !");
                    break;
                }
                case 7: {
                    if (!generalTree.isEmpty())
                        System.out.println(generalTree.preOrder());
                    else
                        System.out.println("The tree is empty !");
                    break;
                }
                case 8: {
                    if (!generalTree.isEmpty())
                        System.out.println(generalTree.postOrder());
                    else
                        System.out.println("The tree is empty !");
                    break;
                }
                case 9: {
                    if (!generalTree.isEmpty())
                        generalTree.numberOfFilesAndFolders(generalTree.root);
                    else
                        System.out.println("The number of folders : " + 0 + "\nThe number of files :" + 0);
                    break;
                }
                case 10: {
                    System.out.println("Terminating ...");
                    break;
                }
            }
            System.out.println();
        } while (num != 10);




    }

    public static int getMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Please select the operation: ");
            System.out.println("1. Create a new tree :");
            System.out.println("2. Add a new folders/files :");
            System.out.println("3. Print the tree in bfs ");
            System.out.println("4. Delete file or folder :");
            System.out.println("5. Print the path for folder/file :");
            System.out.println("6. Sort and print the tree alphabetically :");
            System.out.println("7. Prints the tree in preorder :");
            System.out.println("8. Prints the tree in postorder :");
            System.out.println("9. Print the number of files and folders :");
            System.out.println("10. Exit");

            choice = scanner.nextInt();
            if (choice < 1 || choice > 10)
                System.out.println("Error: Wrong operation!");
        } while (choice < 1 || choice > 10);
        return choice;
    }

    public static void insert(File folder) {
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory() && !fileEntry.getName().equals(".DS_Store")) {
                generalTree.insert(new GeneralTreeNode(fileEntry.getParentFile().getName()),
                        new GeneralTreeNode(fileEntry.getName()));
                insert(fileEntry);
            } else {
                if (!fileEntry.getName().equals(".DS_Store")) {
                    generalTree.insert(new GeneralTreeNode(fileEntry.getParentFile().getName()),
                            new GeneralTreeNode(fileEntry.getName()));
                }
            }
        }
    }

}