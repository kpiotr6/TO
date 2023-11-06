import java.io.*;

public class Saver {
    final private long[][] avg;

    public Saver(long[][] avg) {
        this.avg = avg;
    }
    public void writeTo(String folder,int type){
        String filename = folder+"/"+type+"data"+avg.length+".txt";
        File dir = new File(folder);
        dir.mkdirs();
        File f = new File(filename);
        if(f.exists()){
            f.delete();
        }

        try(Writer writer = new BufferedWriter(new FileWriter(filename))){
            for(int i=0;i<this.avg.length;i++) {
                writer.write(""+i);
                for(int j=0;j<this.avg[i].length;j++){
                    writer.write(";"+avg[i][j]);
                }
                writer.write("\n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
