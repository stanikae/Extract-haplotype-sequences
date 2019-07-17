import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Stanford
 */
public class GetSequences {
    public static void main(String[]args)throws Exception{
        
        ArrayList<String> searchLists= new ArrayList<String>();
        Scanner input = new Scanner (new File("/path/to/refDPB1.fa"));
        Scanner in = new Scanner (new File("/path/to/patient haplotype data"));
        PrintWriter file =new PrintWriter("/path/to/output-file");
                
        StringBuilder sb=new StringBuilder();
        
        //get the SNP positions and load them to array list
        while(in.hasNext()){
            String m=in.nextLine();
            if(m.startsWith("#")){
                continue;
            }
            searchLists.add(m);
        }
        String str = "";
        String seq = "";
        String y="";
        
        //get the multiple line fasta file and concantenate into one contiguous sequence
        while(input.hasNext()){
            seq = input.nextLine();
            if(seq.startsWith(">")){
                continue;
            }
            sb.append(seq);
        }
        
        
        int len = sb.length();
        System.out.println(len);
        
        
        for(String j: searchLists){
                String A[]=j.split(",");
                int x = Integer.valueOf(A[0]);
                int x1 = x-33042876;
                int x2 = A[2].length();
                y = sb.substring(x1, x1+x2);
                System.out.println(x+"\t"+y);
                sb.replace(x1, x1+x2, A[5]); //replace SNPs
        }
        file.print(sb);
        file.close();
    }
}
