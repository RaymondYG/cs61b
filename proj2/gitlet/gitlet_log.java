package gitlet;

import java.io.File;
import java.util.List;

import static gitlet.Utils.join;
import gitlet.Utils.*;
public class gitlet_log {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static void log(){
        String pointer_hash = Utils.readContentsAsString(join(GITLET_DIR,"header"));
        do{
            commit_object pointer = Utils.readObject(join(GITLET_DIR, pointer_hash), commit_object.class);
            System.out.println("===");
            System.out.println("commit " + pointer_hash);
            System.out.println("Date: " + pointer.timestamp);
            System.out.println(pointer.message);
            System.out.println();
            pointer_hash = pointer.parent_ref;
        }while (pointer_hash != null);
    }
    public static void global_log(){
        List<String> array = Utils.plainFilenamesIn(GITLET_DIR);
        for (String file : array){
            if (file.equals("header")){
                continue;
            }
            commit_object pointer = Utils.readObject(join(GITLET_DIR, file), commit_object.class);
            System.out.println("===");
            System.out.println("commit " + file);
            System.out.println("Date: " + pointer.timestamp);
            System.out.println(pointer.message);
            System.out.println();
        }
    }
    public static void find(String message){
        List<String> array = Utils.plainFilenamesIn(GITLET_DIR);
        for (String file : array){
            if (file.equals("header")){
                continue;
            }
            commit_object pointer = Utils.readObject(join(GITLET_DIR, file), commit_object.class);
            if (pointer.message.equals(message)){
                System.out.println("===");
                System.out.println("commit " + file);
                System.out.println("Date: " + pointer.timestamp);
                System.out.println(pointer.message);
                System.out.println();
            }
        }
    }
}
