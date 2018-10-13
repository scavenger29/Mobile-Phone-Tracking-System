import java.util.*;
public class Myset
{
    list l1;
    Myset()
    {
    	l1=null;
    }
	public Boolean IsEmpty()
	{
		if(l1==null)
			return true;
		else
			return false;
	}
	public Boolean IsMember(Object o)
	{
		list ptr;
		for(ptr=l1;ptr!=null && ptr.first!=o;ptr=ptr.next){}
		if (ptr!=null) 
		{
			return true;	
		}
		else
			return false;
	}
	public Boolean mem(int o)
	{
		list ptr;
		for(ptr=l1;ptr!=null && ptr.mobile.mno!=o;ptr=ptr.next){}
		if (ptr!=null) 
		{
			return true;	
		}
		else
			return false;
	}
	public void Insert(Object o)
	{
		list ptr;
		for(ptr=l1;ptr!=null && ptr.first!=o ;ptr=ptr.next){}
		try
		{
			if(ptr==null)
			{ 
				l1=new list(o,l1);
			}
			else 
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			System.out.println("already present");
		}
	}
	public void Inser(int o)
	{
		list ptr;
		for(ptr=l1;ptr!=null && ptr.mobile.mno!=o ;ptr=ptr.next){}
		try
		{ 
			if(ptr==null)
			{ 
				l1=new list(o,l1);
			}
			else 
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			System.out.println("already present");
		}
	}
	public void Delete(Object o)
	{
		try
		{

			if(l1!=null)
			{
				
				if(l1.first==o)
					l1=l1.next;
			
				else
				{
					list ptr;
					for(ptr=l1;ptr.next!=null && ptr.next.first!=o;ptr=ptr.next){}
						if(ptr.next!=null)
							ptr.next=ptr.next.next;
					
				}
			}
			else
				throw new Exception();

		}
		catch(Exception en)
		{
			System.out.println("Not present");
		}
	}
	public void Del(int o)
	{	
		try
		{		
			if(l1!=null)	
			{
				if(l1.mobile.mno==o)
				l1=l1.next;
		
				else
				{
					list ptr;
					for(ptr=l1;ptr.next!=null && ptr.next.mobile.mno!=o;ptr=ptr.next){}
						if(ptr.next!=null)
							ptr.next=ptr.next.next;
					
				}
			}
			else
				throw new Exception();

		}
		catch(Exception en)
		{
			System.out.println("Not present");
		}			
	}

	public Myset Union(Myset a)
	{
		Myset s=new Myset();
		s.l1=null;
		list ptr1,ptr2;
		for(ptr1=l1;ptr1!=null;ptr1=ptr1.next)
		{
			s.Insert(ptr1.first);
		}
		for(ptr1=l1;ptr1!=null;ptr1=ptr1.next)
		{
			for(ptr2=a.l1;ptr2!=null;ptr2=ptr2.next)
			{
				if(ptr1.first!=ptr2.first)
				{
					s.Insert(ptr2.first);
				}
			}
		}
		return s;
	}
	public Myset Intersection(Myset a)
	{
		Myset s=new Myset();
		s.l1=null;
		list ptr1, ptr2;
		for(ptr1=l1;ptr1!=null;ptr1=ptr1.next)
		{
			for(ptr2=a.l1;ptr2!=null;ptr2=ptr2.next)
			{
				if(ptr1.first==ptr2.first)
				{
					s.Insert(ptr1.first);
					break;
				}
			}
		}
		return s;

	}
	public void print()
	{
		list ptr;
		try
		{
			if(l1!=null)
			{
				for(ptr=l1;ptr!=null;ptr=ptr.next)
				{
					System.out.println(ptr.mobile.mno);
				}
			}
			else
				throw new Exception();
		}
		catch(Exception en)
		{
			System.out.println("resident set empty");
		}
		
	}
	private static class list
	{
		Object first;
		list next;
		MobilePhone mobile=new MobilePhone(-1);
		list()
		{
			next=null;
		}
		list(Object f, list n)
		{
			first=f;
			next=n;
		}
		list(int f,list n)
		{
			mobile.mno=f;
			next=n;
		}

	}
}

class MobilePhone
{
	public int mno;
	public Boolean stat;
	public Exchange location;
	MobilePhone(int number)
	{
		mno=number;
		stat=true;

	}
	public int number()
	{
		return mno;
	}
	public Boolean status()
	{
		return stat;
	}
	public void switchOn()
	{
		stat=true;
	}
	public void switchoff()
	{
		stat=false;
	}
	public Exchange location()
	{
		return location;

	}
}
class MobilePhoneSet
{
	Myset set=new Myset();
}
class ExchangeList
{

	ExchangeList top;
	Exchange data;
	ExchangeList ()
	{
		top=null;
		data=null;
	}
	ExchangeList(Exchange a, ExchangeList l)
	{
		data=a;
		top=l;
	}
}
class Exchange
{
	ExchangeList children;
	Exchange parent;
	MobilePhoneSet collect=new MobilePhoneSet();
	int id;

	Exchange(int number)
	{
		id=number;
		parent=null;
	}
	public Exchange parent()
	{
		return parent;
	}
	public int numChildren()
	{
		ExchangeList ptr;
		int i=0;
		for(ptr=children;ptr!=null;ptr=ptr.top)
		{
			i++;
		}
		return i;
	}
	public Exchange child (int i)
	{
		ExchangeList ptr=new ExchangeList();
		ptr=children;
		int j=0;
		if(ptr!=null)
		{
			for(;ptr!=null && j!=i;ptr=ptr.top)
			{
				j++;
			}		
			return ptr.data;	
		}
		else
		{
			Exchange obj=new Exchange(-1);
			return obj;
		}

		
	}
	public Boolean isRoot()
	{
		if(parent==null)
		{
			return true;
		}
		else
			return false;
	}
	public RoutingMapTree subtree(int i)
	{
		Exchange ret=new Exchange(0);
		ret=child(i);
		RoutingMapTree tree=new RoutingMapTree(ret);
		return tree;
	}
	public MobilePhoneSet residentSet()
	{
		
		return  collect;
	}
}
