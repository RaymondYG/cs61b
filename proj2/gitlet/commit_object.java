package gitlet;
import gitlet.Dumpable.*;
import java.util.Date;
import java.util.Formatter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class commit_object implements Dumpable{
    public void dump() {}
    private Date timestamp;
    private String message;
    private List<File> file_ref_list;
    private String branch_ref;
    public commit_object(){
        this.timestamp = new Date();
        this.timestamp.setTime(0);
        this.message = "initial commit";
        this.file_ref_list = null;
        this.branch_ref = "master";
    }


}
