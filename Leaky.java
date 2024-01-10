import java.util.*;

public class Leaky 
{
    public static void main(String[] args) 
    {
        Scanner my = new Scanner(System.in);
        int no_groups, bucket_size;
        System.out.println("Enter the bucket size: ");
        bucket_size = my.nextInt();
        System.out.println("Enter the no. of groups: ");
        no_groups = my.nextInt();
        int no_packets[] = new int[no_groups];
        int in_bw[] = new int[no_groups];
        int out_bw, reqd_bw = 0, tot_packets = 0;

        for (int i=0; i<no_groups; i++)
        {
            System.out.println("Enter the no. of packets for group " + (i+1) + "\t");
            no_packets[i] = my.nextInt();
            System.out.println("Enter the input bandwidth for the group " + (i+1) + "\t");
            in_bw[i] = my.nextInt();

            if((tot_packets + no_packets[i]) <= bucket_size)
                tot_packets += no_packets[i];
            else
            {
                tot_packets += no_packets[i];
                int drop = tot_packets - bucket_size;
                System.out.println("Bucket Overflow!");
                System.out.println(drop + " packets are dropped!");
                tot_packets -= drop;
                no_packets[i] -= drop;
            }
            reqd_bw += (no_packets[i] * in_bw[i]);
        }
        System.out.println("The total required bandwidth is " + reqd_bw);
        System.out.println("Enter the output bandwidth: ");
        out_bw = my.nextInt();
        int temp = reqd_bw;
        int rem_pkts = tot_packets;

        while ((out_bw <= temp) && (rem_pkts > 0)) 
        {
            System.out.println("Data sent \n" + (--rem_pkts) + " packets remaining");
            System.out.println("Remaining Bandwidth " + (temp -= out_bw));
            System.out.println("");

            if ((out_bw > temp) && (rem_pkts > 0)) 
            {
                System.out.println(rem_pkts + "packets are not confirmed due to insufficient bandwidth!");
            }
        }
    }
}