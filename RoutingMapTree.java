import java.util.*;
public class RoutingMapTree
{
	Exchange root;

	public RoutingMapTree() 
	{
		root=new Exchange(0);

	}
	public RoutingMapTree(Exchange ret)
	{
		root=ret;
	}
	public Boolean containsNode(Exchange a)
	{
		Boolean temp=false;
		Exchange obj=new Exchange(a.id);
		if(root.id==a.id)
		{
			temp=true;
		}
		else if(root!=null)
		{
			for(int i=0;i<root.numChildren();i++)
			{	
				temp=root.subtree(i).containsNode(a);
				if(temp==true)
					break;
			}
			
		}
		else
			temp=false;
		return temp;		
	}
	public Exchange search(int a)
	{
		
		Exchange obj5= new Exchange(-1);
		if(root.id==a)
		{
			obj5=root;		
		}
		else 
		{
			for(int i=0;i<root.numChildren();i++)
			{	
				obj5=root.subtree(i).search(a);
				if(obj5.id==a)
					break;

			}
			
		}		return obj5;
	}
	public void switchOn(MobilePhone an, Exchange  obj)
	{
		Exchange b=search(obj.id);
		MobilePhone a=new MobilePhone(an.mno);
		a.location=b;
		while(b!=null)
		{
			b.collect.set.Inser(a.mno);
			b=b.parent();
			
		}
	}
	public void switchOff(MobilePhone a)
	{
		Exchange ptr;
		ptr=root;
		ptr.collect.set.Del(a.mno);	
		for(int i=0;i<ptr.numChildren();i++)
		{
			if(ptr.child(i).collect.set.mem(a.mno)==true)
			{ptr.subtree(i).switchOff(a);break;
			}
		}
	}
	public Exchange findPhone(MobilePhone m)
	{
		Exchange ptr;
		Exchange red;
		ptr=root;
		red=root;
		for(int i=0;i<ptr.numChildren();i++)
		{
			if(ptr.child(i).collect.set.mem(m.mno)==true)
			{red=ptr.subtree(i).findPhone(m);break;}
		}
		return red;
	}
	public Exchange lowestRouter(Exchange a, Exchange b)
	{
		Exchange obj;
		obj=search(a.id);
		Exchange boj;
		boj=search(b.id);		
		int flag=0;
		if(obj.id==b.id)
		{
			return obj;
		}
		else
		{
			int hta=0,htb=0;
			Exchange ptr=obj;
			while(ptr!=null)
			{
				ptr=ptr.parent();
				hta++;
			}
			ptr=boj; 
			while(ptr!=null)
			{
				ptr=ptr.parent();
				htb++;
			}
			if(hta>htb)
			{
				while(hta!=htb)
				{
					obj=obj.parent();
					hta--;
				}
			}
			else if(htb<htb)
			{
				while(hta!=htb)
				{
					boj=boj.parent();
					htb--;
				}
			}
			while(obj.parent().id!=boj.parent().id)
			{
				obj=obj.parent();
				boj=boj.parent();
			}
			return obj.parent();
		}
		

	}
	public ExchangeList routeCall(MobilePhone a,MobilePhone b)
	{
		ExchangeList out=new ExchangeList();
		ExchangeList last;
		Exchange loc=findPhone(a);
		Exchange loc_b=findPhone(b);
		Exchange ret=lowestRouter(loc,loc_b);
		out.data=loc;
		out.top=null;
		last=out;
		while(loc.id!=ret.id)
		{

			loc=loc.parent();
			ExchangeList ptr=new ExchangeList();
			ptr.data=loc;
			ptr.top=null;
			last.top=ptr;
			last=ptr;
		}
		ExchangeList out_b=new ExchangeList();
		out_b=null;
		while(loc_b.id!=ret.id)
		{
			out_b= new ExchangeList(loc_b,out_b);
			loc_b=loc_b.parent();
		}
		last.top=out_b ;
		return out;
	}
	public void movePhone(MobilePhone a, Exchange b)
	{
		switchOff(a);
		MobilePhone mob=new MobilePhone(a.mno);
		switchOn(mob,b);
	}
	public void performAction(String actionMessage) {
		System.out.println(actionMessage);
		String[] str = actionMessage.split("\\s+");		
		{
			if(str[0].equals("addExchange"))
			{
				
				int  a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				Boolean res;
				Exchange obj=new Exchange(a);
				res=containsNode(obj);
				try
				{
					if(res==true)
					{
						obj=search(a);
						Exchange obj1=new Exchange(b);
						obj1.parent=obj;
						ExchangeList ptr;
						ExchangeList list=new ExchangeList();
						ExchangeList last=new ExchangeList();
						list.data=obj1;
						list.top=null;
						ptr=obj.children;
						if(ptr==null)
						{
							obj.children=list;
						}	
						else
						{
							for(;ptr!=null;ptr=ptr.top)
							{
								
								last=ptr;
							}
						last.top=list;}			
					}
					else
					{
						throw new Exception();
					}
				}	
				catch(Exception en)
				{
					System.out.println("exchange not found");
				}			
			}
			else if(str[0].equals("switchOnMobile"))
			{
				int a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				Boolean res;
				Exchange obj=new Exchange(b);
				res=containsNode(obj);
				try
				{
					if(res==true)
					{	obj=search(b);
						MobilePhone newmob=new MobilePhone(a);
						switchOn(newmob,obj);
					}
					else
						throw new Exception();
				}
				catch(Exception en)
				{
					System.out.println("exchange not present");
				}
			}
			else if(str[0].equals("switchOffMobile"))
			{
				int a=Integer.parseInt(str[1]);
				Boolean ret;
				ret=root.collect.set.mem(a);

				try
				{
					if(ret==true)
					{	
						MobilePhone newmob=new MobilePhone(a);
						switchOff(newmob);
					}
					else
					{
						throw new Exception();
					}
				}
				catch (Exception en)
				{
					
					System.out.println("no mobile phone");
				}
				
			}
			else if(str[0].equals("queryNthChild"))
			{
				int  a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				Exchange obj=new Exchange(a);
				Boolean ret;
				ret=containsNode(obj);
				try
				{
					if(ret==true)
					{
						obj=search(a);
						try
						{
							if(obj.numChildren()>0)
							{
								Exchange chi=new Exchange(0);
								chi=obj.child(b);
								if(chi.id>=0)
								System.out.println(chi.id);
							}
							else
							{
								throw new Exception ();
							}
						}
						catch (Exception e)
						{
							System.out.println("Child does not exist");
						}
						
					}
					else
					{
						throw new Exception();
					}
				}
				catch (Exception en)
				{
					System.out.println("exchange not present");
				}
			}
			else if(str[0].equals("queryMobilePhoneSet"))
			{
				int a=Integer.parseInt(str[1]);
				Exchange obj=new Exchange(a);
				Boolean ret;
				ret=containsNode(obj);
				try
				{
					if(ret==true)
					{
						obj=search(a);
						obj.collect.set.print();
					}
					else
						throw new Exception();
				}
				catch  (Exception en)
				{
					System.out.println("no such exchange present");
				}
			}
			else if(str[0].equals("queryFindPhone"))
			{
				int a=Integer.parseInt(str[1]);
				MobilePhone m=new MobilePhone(a);
				Boolean ret;
				ret=root.collect.set.mem(a);
				try
				{
					if(ret==true)
					{
						System.out.println(findPhone(m).id);
					}
					else
					{
						throw new Exception();
					}
				}
				catch (Exception en)
				{
					System.out.println("mobile phone does not exist");
				}
			}
			else if(str[0].equals("queryLowestRouter"))
			{
				int  a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				Exchange obj=new Exchange(a);
				Exchange boj=new Exchange(b);
				Boolean res=containsNode(obj);
				Boolean ret=containsNode(boj);
				try
				{
					if(res && ret)
					{
						System.out.println(lowestRouter(obj,boj).id);
					}
					else
					{
						throw new Exception();
					}
				}	
				catch(Exception en)
				{
					System.out.println("exchange does not exist");
				}
			}
			else if(str[0].equals("queryFindCallPath"))
			{
				int  a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				MobilePhone mob1=new MobilePhone(a);
				MobilePhone mob2=new MobilePhone(b);
				Boolean res=root.collect.set.mem(a);
				Boolean ret=root.collect.set.mem(b);
				try
				{
					if(res && ret)
					{
						ExchangeList list=routeCall(mob1,mob2);
						ExchangeList ptr;
						ptr=list;
						while(ptr!=null)
						{
							System.out.println(ptr.data.id);
							ptr=ptr.top;
						}
					}
					else
					{
						throw new Exception();
					}
				}	
				catch(Exception en)
				{
					System.out.println("mobilephone does not exist");
				}
					
			}
			else if(str[0].equals("movePhone"))
			{
				int  a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				Exchange obj=new Exchange(b);
				MobilePhone mob=new MobilePhone(a);
				Boolean ret=containsNode(obj);
				try
				{
					if(ret)
					{
						movePhone(mob,obj);
					}
					else
					{
						throw new Exception();
					}
				}
				catch(Exception en)
				{
					System.out.println("exchange not present");
				}
			}
			else
			{
				System.out.println("nopes");
			}
		}	

	}
}
