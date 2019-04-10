 import java.io.*;
import java.util.Random;
import javax.swing.*;
 

 
public class MultiServerQueueingSystem
{
static Server server1;
static Server server2;
static Server server3;
static Server server4;
static Server server5;
static Random random;
static int is_server4 = 0;
 
public static void main(String[] args)throws IOException
{
server1 = new Server();
server2 = new Server();
server3 = new Server();
server4 = new Server();
server5 = new Server();
 
server1.mean_interarrival=Double.valueOf(JOptionPane.showInputDialog("Enter Mean Interarrival time:"));
server2.mean_interarrival=server1.mean_interarrival;
server3.mean_interarrival=server1.mean_interarrival;
 
server1.mean_service=Double.valueOf(JOptionPane.showInputDialog("Enter Mean Service time of server1:"));
server2.mean_service=Double.valueOf(JOptionPane.showInputDialog("Enter Mean Service time of server2:"));
server3.mean_service=Double.valueOf(JOptionPane.showInputDialog("Enter Mean Service time of server3:"));
server4.mean_service=Double.valueOf(JOptionPane.showInputDialog("Enter Mean Service time of server4:"));
server5.mean_service=Double.valueOf(JOptionPane.showInputDialog("Enter Mean Service time of server5:"));
 
int total_customer = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Customer:"));
 
System.out.println("Double-server queueing system\n");
System.out.println("Mean interarrival time  "+server1.mean_interarrival+" minutes\n");
System.out.println("Mean service time of server1 "+server1.mean_service+" minutes\n");
System.out.println("Mean service time of server2 "+server2.mean_service+" minutes\n");
System.out.println("Mean service time of server3 "+server3.mean_service+" minutes\n");
System.out.println("Mean service time of server4 "+server4.mean_service+" minutes\n");
System.out.println("Mean service time of server5 "+server5.mean_service+" minutes\n");
System.out.println("Number of customer  "+ total_customer + "\n\n");
 
random = new Random(total_customer);
 
server1.initialize();
server2.initialize();
server3.initialize();
server4.initialize();
server5.initialize();
 
int option = JOptionPane.showConfirmDialog(null, "Are you want to end by simulation time?\nIf No it simulated by total customer.");
 
if(option==1)
{
while(server3.num_custs_delayed < total_customer)
{
double choice = random.nextDouble();
if(choice <= 0.8)
{
server1_activity();
}
else if(choice <= 0.95)
{
server2_activity();
}
else
{
server3_activity(server3.sim_time + expon(server3.mean_interarrival));
}
}
}
else if(option==0)
{
double sim_time = Double.valueOf(JOptionPane.showInputDialog("Enter simulation time:"));
while(server3.sim_time < sim_time)
{
double choice = random.nextDouble();
if(choice <= 0.8)
{
server1_activity();
}
else if(choice <= 0.95)
{
server2_activity();
}
else
{
server3_activity(server3.sim_time + expon(server3.mean_interarrival));
}
}
}
else
{
System.exit(0);
}
 
System.out.println("\n\nReport for server 1:\n--------------------\n");
server1.report();
System.out.println("\n\nReport for server 2:\n--------------------\n");
server2.report();
System.out.println("\n\nReport for server 3:\n--------------------\n");
server3.report();
System.out.println("\n\nReport for server 4:\n--------------------\n");
server4.report();
System.out.println("\n\nReport for server 5:\n--------------------\n");
server5.report();
System.out.println("\n\nTime simulation ended minutes  "+ (server1.sim_time+server2.sim_time+server3.sim_time+server4.sim_time+server5.sim_time) +"\n");
System.out.println("Avarage waiting time in system "+ (server3.sim_time / server3.num_custs_delayed) +"\n");
}
 
private static void server1_activity()
{
server1.timing();
 
server1.update_time_avg_stats();
 
switch (server1.next_event_type)
{
case 1: server1.arrive(server1.sim_time+expon(server1.mean_interarrival));
break;
case 2: server1.depart();
server3_activity(server1.sim_time);
server1_activity();
}
}
 
private static void server2_activity()
{
server2.timing();
 
server2.update_time_avg_stats();
 
switch (server2.next_event_type)
{
case 1: server2.arrive(server2.sim_time+expon(server2.mean_interarrival));
break;
case 2: server2.depart();
server3_activity(server2.sim_time);
server2_activity();
}
}
 
private static void server3_activity(double time)
{
server3.timing();
 
server3.update_time_avg_stats();
 
switch (server3.next_event_type)
{
case 1: server3.arrive(time);
break;
case 2: server3.depart();
if(server4.num_in_q < server5.num_in_q)
{
server4_activity(server3.sim_time);
is_server4 = 1;
}
else if(server5.num_in_q < server4.num_in_q)
{
server5_activity(server3.sim_time);
is_server4 = 0;
}
else
{
if(is_server4 == 1)
{
server5_activity(server3.sim_time);
is_server4 = 0;
}
else
{
server4_activity(server3.sim_time);
is_server4 = 1;
}
}
server3_activity(time);
}
}
 
private static void server4_activity(double time)
{
server4.timing();
 
server4.update_time_avg_stats();
 
switch (server4.next_event_type)
{
case 1: server4.arrive(time);
break;
case 2: server4.depart();
server4_activity(time);
}
}
 
private static void server5_activity(double time)
{
server5.timing();
 
server5.update_time_avg_stats();
 
switch (server5.next_event_type)
{
case 1: server5.arrive(time);
break;
case 2: server5.depart();
server5_activity(time);
}
}
 
static double expon(double  mean)
{
return -mean * Math.log(random.nextDouble());
}
}