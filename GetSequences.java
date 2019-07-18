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
        
        Scanner input = new Scanner (new File("ExampleData\\refDPB1.fa")); // HLA-DPB1 nucleotide file from hg37 reference sequence
        Scanner in = new Scanner (new File("ExampleData\\Examble01.data_rev.txt"));
        PrintWriter fwd_seq =new PrintWriter("ExampleData\\Examble01_fwd.fa"); // fwd sequence output
        PrintWriter rev_seq =new PrintWriter("ExampleData\\Examble01_rev.fa"); // rev sequence output
                
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
        
        // get sequences with haplotypes on fwd strand
        for(String j: searchLists){
                String A[]=j.split(",");
                int x = Integer.valueOf(A[0]);
                int x1 = x-33042876;
                int x2 = A[2].length();
                y = sb.substring(x1, x1+x2);
                System.out.println(x+"\t"+y);
                sb.replace(x1, x1+x2, A[4]); // SNPs on fwd strand
                
        }
        
        // write sequence with forward haplotypes (SNPs/indels) to file
        fwd_seq.println(">Forward_haplotype_sequence");   
        fwd_seq.print(sb);
        fwd_seq.close();
                
        // get sequences with haplotypes on rev strand
        for(String j: searchLists){
                String A[]=j.split(",");
                int x = Integer.valueOf(A[0]);
                int x1 = x-33042876;
                int x2 = A[2].length();
                y = sb.substring(x1, x1+x2);
                System.out.println(x+"\t"+y);
                sb.replace(x1, x1+x2, A[5]); // SNPs on rev strand
            }
        
        // write sequence with reverse haplotypes (SNPs/indels) to file
        rev_seq.println(">Reverse_haplotype_sequence");
        rev_seq.print(sb);
        rev_seq.close();

    }
}
