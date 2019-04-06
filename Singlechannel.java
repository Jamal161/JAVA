
package javaapplication31;

import java.io.*;

	public class Singlechannel
	{ 
 public static void main(String a[])throws Exception
	{	
	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	float avgser=0,qavg=0,savg=0;
	int i,no,wcount=0,icount=0,j;
	System.out.println("ENTER THE NUMBER OF CUSTOMERS.");
	no=Integer.parseInt(br.readLine());
	int arrival[]={1,2,3,4,5,6,7,8};
	int arrivalrda[]=new int[arrival.length];
	float cumarrival[]=new float[arrival.length];
	float arrivalprob[]={0.125f,0.125f,0.125f,0.125f,0.125f,0.125f,0.125f,0.125f};
	int arrivaltime[]=new int[no];
	int service[]={1,2,3,4,5,6};
	float serviceprob[]={0.10f,0.20f,0.30f,0.25f,0.10f,0.05f};
	float servicecum[]=new float[service.length];
	int servicerda[]=new int[service.length];
	int servicetime[]=new int[no];
	int randomarrival[]=new int[no];
	int randomservice[]=new int[no];
	int interarrival[]=new int[no];
	int starttime[]=new int[no];
	int endtime[]=new int[no];
	int ttq[]=new int[no];
	int tts[]=new int[no];
	int idletime[]=new int[no];
	for(i=0;i<arrival.length;i++)
	{	if(i==0)
	{	cumarrival[i]=arrivalprob[i];
	arrivalrda[i]=(int)(cumarrival[i]*100);
	}

	else
	{	cumarrival[i]=cumarrival[i-1]+arrivalprob[i];
	arrivalrda[i]=(int)(cumarrival[i]*100);
	}
	}
	for(i=0;i<service.length;i++)
	{	if(i==0)
	{	servicecum[i]=serviceprob[i];
	servicerda[i]=(int)(servicecum[i]*100);
	}
	else
	{	servicecum[i]=servicecum[i-1]+serviceprob[i];
	servicerda[i]=(int)(servicecum[i]*100);
	}
	}
	randomservice[0]=(int)(Math.random()*100d);
	for(i=1;i<no;i++)
	{	randomarrival[i]=(int)(Math.random()*100d);
	randomservice[i]=(int)(Math.random()*100d);
	}
	for(i=1;i<no;i++)
	{	for(j=0;j<arrivalrda.length;j++)
	{	if(randomarrival[i]==0)
	{	arrivaltime[i]=arrival[arrival.length-1];
	break;
	}
	if(randomarrival[i]<=arrivalrda[j])
	{	arrivaltime[i]=arrival[j];
	break;
	}
	}
	interarrival[i]=interarrival[i-1]+arrivaltime[i];
	}
	for(i=0;i<no;i++)
	{	for(j=0;j<servicerda.length;j++)
	{	if(randomservice[i]==0)
	{	servicetime[i]=service[service.length-1];
	break;
	}
	if(randomservice[i]<=servicerda[j])
	{	servicetime[i]=service[j];
	break;
	}
	}
	}
	for(i=0;i<no;i++)
	{	if(i==0)
	{	starttime[i]=0;
	endtime[i]=starttime[i]+servicetime[i];
	tts[i]=servicetime[i];
	}
	else
	{	if(endtime[i-1]>=interarrival[i])
	{	ttq[i]=endtime[i-1]-interarrival[i];
	starttime[i]=endtime[i-1];
	endtime[i]=starttime[i]+servicetime[i];
	tts[i]=servicetime[i]+ttq[i];
	}
	else
	{	idletime[i]=interarrival[i]-endtime[i-1];
	starttime[i]=interarrival[i];
	endtime[i]=starttime[i]+servicetime[i];
	tts[i]=servicetime[i];
	}
	}
	}
	for(i=0;i<no;i++)
	{	avgser=avgser+servicetime[i];
	qavg=qavg+ttq[i];
	savg=savg+tts[i];
	if(ttq[i]>0)
	wcount++;
	if(idletime[i]>0)
	icount++;
	}
	System.out.println("No.\tRDA.\tARR.\tIARR.\tRDS.\tPSTAT.\tSERT.\tPEND.\tTSQ.\tIDLW.\tTIS.");
	for(i=0;i<no;i++)		{
	System.out.println((i+1)+"\t"+randomarrival[i]+"\t"+arrivaltime[i]+"\t"+interarrival[i]+"\t"+randomservice[i]+"\t"+starttime[i]+"\t"+servicetime[i]+"\t"+endtime[i]+"\t"+ttq[i]+"\t"
	+idletime[i]+"\t"+tts[i]);

	}

	System.out.println("PERFORMANCE OF THE SYSTEM IS AS FOLLOWS:");

	System.out.println("AVERAGE SERVICE TIME:"+(avgser/no));

	if(wcount>0)

	System.out.println("AVERAGE WAITING TIME OF THE CUSTOMER IS:"+(qavg/wcount));

	System.out.println("PROBABILITY OF WAITING IS:"+((float)wcount/no));

	System.out.println("AVERAGE TIME SPENT BY CUSTOMER IN THE SYSTEM IS:"+(savg/no));

	System.out.println("PROBABILITY OF IDLE SERVER IS:"+((float)icount/no));
	}
	}