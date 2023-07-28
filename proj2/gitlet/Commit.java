package gitlet;

// TODO: any imports you need here

import java.io.File;
import gitlet.commit_object.*;
import java.util.Date; // TODO: You'll likely use this in this class

import static gitlet.Utils.join;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    public static String dump_commit(commit_object tmp){
        String sha = Utils.sha1(Utils.serialize(tmp));
        File GITLET_DIR = join(new File(System.getProperty("user.dir")), ".gitlet");
        File file_tmp = join(GITLET_DIR, sha);
        Utils.writeObject(file_tmp, tmp);
        return sha;
    }
    public static void dump_header(String commit_obj_hash) {
        File header = join(new File(System.getProperty("user.dir")), ".gitlet","header");

        Utils.writeContents(header, commit_obj_hash);
    }

    /* TODO: fill in the rest of this class. */
}
