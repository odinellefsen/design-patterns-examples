// Component from the class diagram
abstract class FileSystemComponent {
    protected String name;
    
    public FileSystemComponent(String name) {
        this.name = name;
    }
    
    // operation() from the class diagram
    public abstract void display(int depth);
    
    // Additional operation for our example
    public abstract int getSize();
    
    // These methods will be implemented by Composite but not by Leaf
    public void addComponent(FileSystemComponent component) {
        throw new UnsupportedOperationException();
    }
    
    public void removeComponent(FileSystemComponent component) {
        throw new UnsupportedOperationException();
    }
    
    public FileSystemComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }
}

// Leaf from the class diagram
class File extends FileSystemComponent {
    private int size; // Size in KB
    
    public File(String name, int size) {
        super(name);
        this.size = size;
    }
    
    // operation() implementation
    @Override
    public void display(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        System.out.println(indent + "File: " + name + " (" + size + " KB)");
    }
    
    @Override
    public int getSize() {
        return size;
    }
}

// Composite from the class diagram
class Directory extends FileSystemComponent {
    private java.util.ArrayList<FileSystemComponent> children;
    
    public Directory(String name) {
        super(name);
        children = new java.util.ArrayList<>();
    }
    
    // operation() implementation
    @Override
    public void display(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        System.out.println(indent + "Directory: " + name);
        
        // Display each child
        for (FileSystemComponent component : children) {
            component.display(depth + 1);
        }
    }
    
    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
    
    // addComponent() implementation
    @Override
    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }
    
    // removeComponent() implementation
    @Override
    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }
    
    // getChild() implementation
    @Override
    public FileSystemComponent getChild(int index) {
        return children.get(index);
    }
}

@SuppressWarnings("all")
public class Composite {
    public static void main(String[] args) {
        // Create a file system structure
        
        // Create files (leaf nodes)
        FileSystemComponent file1 = new File("document.txt", 250);
        FileSystemComponent file2 = new File("image.jpg", 2048);
        FileSystemComponent file3 = new File("spreadsheet.xlsx", 750);
        
        // Create directories (composite nodes)
        Directory documents = new Directory("Documents");
        Directory images = new Directory("Images");
        Directory root = new Directory("Root");
        
        // Build the file structure
        documents.addComponent(file1);
        documents.addComponent(file3);
        images.addComponent(file2);
        
        root.addComponent(documents);
        root.addComponent(images);
        
        // Add another level
        Directory downloads = new Directory("Downloads");
        downloads.addComponent(new File("program.exe", 4096));
        root.addComponent(downloads);
        
        // Display the entire file structure
        System.out.println("File Structure:");
        root.display(0);
        
        // Calculate total size
        System.out.println("\nTotal size: " + root.getSize() + " KB");
        
        // Remove a component
        System.out.println("\nRemoving Images directory...");
        root.removeComponent(images);
        
        // Display updated structure
        System.out.println("\nUpdated File Structure:");
        root.display(0);
        
        // Calculate new total size
        System.out.println("\nNew total size: " + root.getSize() + " KB");
    }
}