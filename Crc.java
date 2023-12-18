import java.util.Scanner;

class Crc
{
    public String crc (String dividend, String divisor)
    {
        int shift;
        shift = dividend.length() - divisor.length();

        while (shift >= 0)
        {
            dividend = Integer.toBinaryString(Integer.parseInt(dividend, 2) ^ (Integer.parseInt (divisor, 2) << shift));
            shift = dividend.length() - divisor.length();
        }

        if (dividend.length() < 16)
        {
        while (dividend.length() != 16)
            dividend = "0" + dividend;
        }
        System.out.println("Div = "+dividend);
        return dividend;
    }
    public static void main (String[] args)
    {
    int padding;
    String data, checksum, syn, dividend, Received_data;
    String polynomial = "10001000000100001"; 
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter the data to be encrypted: "); 
    data = sc.next();
    dividend = data;
    padding = polynomial.length() - 1;

    for(int i=0;i < padding;i++)
        dividend += "0";
    
    Crc obj = new Crc();
    checksum = obj.crc(dividend, polynomial);
    data += checksum;
    System.out.println("Sender Checksum = "+checksum); 
    System.out.println("Code word transmitted overnetwork = "+data);
    System.out.print("Enter the received codeword: "); 
    Received_data = sc.next();
    syn = obj.crc(Received_data,polynomial);

    if(Long.parseLong (syn) == 0) 
        System.out.println("No error in data transmission\n"); 
    else
        System.out.println("Error in transmission\n");
    }
}