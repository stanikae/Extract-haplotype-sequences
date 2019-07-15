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
        Scanner input = new Scanner (new File("C:\\Users\\Stanford\\Documents\\PostDoc_SBIMB\\ShayneHaplotypes\\refDPB1.fa"));
        Scanner in = new Scanner (new File("C:\\Users\\Stanford\\Documents\\PostDoc_SBIMB\\ShayneHaplotypes\\NP1070_data_rev.txt"));
        PrintWriter file =new PrintWriter("C:\\Users\\Stanford\\Documents\\PostDoc_SBIMB\\ShayneHaplotypes\\NP1070_pat.fa");
        //StringBuilder sb=new StringBuilder("Hello ");
        //sb.append("Java");//now original string is changed  
        //System.out.println(sb);//prints Hello Java  
        
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
        //int x2 = 0;
        //String mat = "";
        //char y ='\0'; //initializing empty char
        String y="";
        
        //get the multiple line fasta file and concantenate into one contiguous sequence
        while(input.hasNext()){
            seq = input.nextLine();
            if(seq.startsWith(">")){
                continue;
            }
            //StringBuilder sb=new StringBuilder(seq);
            sb.append(seq);
        }
        
        
        int len = sb.length();
        //System.out.println(sb);
        System.out.println(len);
        
        
        for(String j: searchLists){
                String A[]=j.split(",");
                int x = Integer.valueOf(A[0]);
                int x1 = x-33042876;
                //if(A[2].length() > 1){
                    //y = sb.charAt(x1);
                //    int x2 = A[2].length();
                //    y = sb.substring(x1, x1+x2);
                    //sb.replace(x1, x1+x2, A[4]);
                    //System.out.println(x+"\t"+y);
                //} else {
                //    y = sb.substring(x1, x1+1);
                    //sb.replace(x1, x1, A[4]);
                //}
                int x2 = A[2].length();
                y = sb.substring(x1, x1+x2);
                System.out.println(x+"\t"+y);
                sb.replace(x1, x1+x2, A[5]);
                //file.println(j+","+y);
                //file.print(sb);
                
                //replace SNPs
                //sb.replace(x1, x1+x2, A[4]);
                //file.print(sb);
            }
        file.print(sb);
        file.close();
    }
}
