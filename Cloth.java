import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


//===============MAIN CLOTH CLASS START HERE (TO EXCICUTE THE PROGRAM RUN THIS CLASS)==============//
public class Cloth{ 
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		//============CREATE THE CLOTHS START HERE==============//

		AddCloth greenshirt=new AddCloth(1,"Shirt","XXL","green",500,10,1);
		AddCloth redshirt=new AddCloth(2,"Shirt","L","red",400,30,100);

		//============CREATE THE CLOTHS END HERE==============//


		Store Store1= new Store();//CREATE THE STORE


		//============ADD THE CLOTH TO THR STORE==============//

		Store1.AddToStore(greenshirt);
		Store1.AddToStore(redshirt);

		//============ADD THE CLOTH TO THR STORE==============//




		//============ADD COUPON FOR THE STORE==============//

		Store1.AddCouponCode("ATANU",30);
		Store1.AddCouponCode("DUTTA",50);

		//============ADD COUPON FOR THE STORE==============//






		Customer Customer1=new Customer();//CREATE THE CUSTOMER 

		System.out.println("\n\tWELCOME");
		Store1.DisplayForCustomer(); //DISPLAY THE CUSTOMER THE MENU
		while (1==1){
			System.out.println("\n1.Add To Cart\n2.Remove From Cart\n3.Check Cart\n4.Menu\n0.Exit");
			System.out.print("\n Enter Your Choice:- ");
		try{
	        int ch = in.nextInt();
			if (ch==1){
				System.out.println("\nADD TO CART");
				System.out.print("Enter The item ID:- ");
				int itm = in.nextInt();
				Customer1.AddProductToCart(itm,Store1);
			}

			else if (ch==2){
				System.out.println("\nREMOVE FROM CART");
				System.out.print("Enter The item ID:- ");
				int itm = in.nextInt();
				Customer1.RemoveFromCart(itm,Store1);
			}

			else if (ch==3){
				System.out.println("\nCheck CART");
				Customer1.DisplayCartItems(Store1);
			}

			else if (ch==4){
				Store1.DisplayForCustomer();
			}

			else if (ch==0){
				System.out.println("\nexit");
				break;
			}
			else if (ch==5656){
				System.out.println("\nWelcome To Admin Panal");
				while(1==1){
					System.out.println("\n1.Display\n2.Update\n0.Exit");
					System.out.print("\n Enter Your Choice:- ");
					try{
						int adch = in.nextInt();
						if (adch==1){Store1.DisplayForSeller();}
						else if (adch==2){
							System.out.print("Enter The Item Number:-");
							int aditm = in.nextInt();
							Store1.Update(aditm);
						}
						else if (adch==0){
							System.out.println("\nexit");
							break;
						}
					}catch(Exception e){System.out.println("\nINVALID");break;}
				}
			}
			else{
				System.out.println("WRONG INPUT");
			}
		}catch(Exception e){System.out.println("\nINVALID");break;}

		 }
	}
}


//===============MAIN CLOTH CLASS END HERE (TO EXCICUTE THE PROGRAM RUN THIS CLASS)==============//

//======================ADD CLOTHS CLASS START====================//

class AddCloth{
	String clothtype,clothsize,color;
	double price,discount;
	int instock,id;
	AddCloth(int id,String clothtype,String clothsize,String color,double price,double discount,int instock){
		this.id=id;
		this.clothtype=clothtype;
		this.clothsize=clothsize;
		this.color=color;
		this.price=price;
		this.discount=discount;
		this.instock=instock;
	}//AddCloth 
}//class AddCloth

//======================ADD CLOTHS CLASS END====================//



//======================STORE CLASS START====================//

class Store{
	Scanner in = new Scanner(System.in);
	ArrayList<AddCloth> clothList = new ArrayList<AddCloth>();// STORE THE OBJECTS OF CLOTHS
	String codeCouponList[]={}; //STORE THE ALL COUPON CODES
	Double codeCouponDiscountList[]={}; // STORE THE ALL COUPON CODES DISCOUNTS

	public static int findIndex(String arr[], String t){//FOR FIND THE INDEX IN AN ARRAY
        int index = Arrays.binarySearch(arr, t);
        return (index < 0) ? -1 : index;
    }


	void AddCouponCode(String couponCode, double couponDiscount){// FOR ADD THE COUPON CODE TO THE STORE
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(codeCouponList));
		myList.add(couponCode);
		codeCouponList = myList.toArray(codeCouponList);
		ArrayList<Double> myListD = new ArrayList<Double>(Arrays.asList(codeCouponDiscountList));
		myListD.add(couponDiscount);
		codeCouponDiscountList = myListD.toArray(codeCouponDiscountList);
	}//void AddCouponCode


	void AddToStore(AddCloth ClothName){// FOR ADD THE CLOTHS TO THE CLOTHLIST ARRAY     ATANU 
		clothList.add(ClothName);
	}//void AddToStore


	void DisplayForSeller(){// DISPLAY FOR THE SELLER WITH STOCK UPDATE
		String dis="";
		System.out.println("\n\tDisplay With Stock Update");
		System.out.println("\n\tNO\tTYPE\tCOLOR\tSIZE\tPRICE\tDISCOUNT INSTOCK\n");
		for (AddCloth emp : clothList){
			if (emp.instock<=0){
				dis="OUT OF STOCK";
			}
			else{
				dis="";
			}
            System.out.println("\t"+ emp.id+"\t"+emp.clothtype+"\t"+emp.color+"\t"+emp.clothsize+"\t"+emp.price+"\t"+emp.discount+"%"+"\t  "+emp.instock+"\t"+dis);	
		}
		System.out.println("*ALL THE DISCOUNT WILL APPLY LATER WITH THE PRICE");
	}


	void DisplayForCustomer(){// DISPLAY THE MENU FOR CUSTOMER TO CHOOSE 
		String dis="";
		System.out.println("\n\tMENU");
		System.out.println("\n\tNO\tTYPE\tCOLOR\tSIZE\tPRICE\tDISCOUNT\n");
		for (AddCloth emp : clothList){
			if (emp.instock<=0){
				dis="OUT OF STOCK";
			}
			else{
				dis="";
			}
            System.out.println("\t"+ emp.id+"\t"+emp.clothtype+"\t"+emp.color+"\t"+emp.clothsize+"\t"+emp.price+"\t"+emp.discount+"%"+"\t"+dis);	
		}
		System.out.println("*ALL THE DISCOUNT WILL APPLY LATER WITH THE PRICE");
	}



	void Update(int id){// FOR UPDATE THE DATA OF ASSINED CLOTHES
		System.out.println("\nUpdate\nProduct ID is :- "+id);
		System.out.println("\n1.Update The Price\n2.Update The Discount\n3.Update the Stock\nEnter anyother key for exit\n");
		System.out.print("Enter your choice : ");
		try{
	        int ch = in.nextInt();
	        if (ch==1){// FOR UPDATE THE PRICE
	        	System.out.println("Enter the new price for product id "+id);
	        	System.out.print("Enter Here");
	        	double updatePrice=in.nextDouble();
	        	for (AddCloth emp : clothList){
	        		if (emp.id==id){
	        			emp.price=updatePrice;
	        		}
	        	}
	        }
	        else if (ch==2){// FOR UPDATE THE DIACOUNT
	        	System.out.println("Enter the new discount for product id "+id);
	        	System.out.print("Enter Here");
	        	double updateDiscount=in.nextDouble();
	        	for (AddCloth emp : clothList){
	        		if (emp.id==id){
	        			emp.discount=updateDiscount;
	        		}
	        	}
	        }
	        else if (ch==3){// FOR UPDATE THE STOCK OF THE PRODUCT
	        	System.out.println("Enter the new stock for product id "+id);
	        	System.out.print("Enter Here");
	        	int updateStock=in.nextInt();
	        	for (AddCloth emp : clothList){
	        		if (emp.id==id){
	        			emp.instock=updateStock;
	        		}
	        	}
	        }
	        else{
	        	System.out.println("\nExit From Update");
	        }
	    }
	    catch (Exception e) {
	      System.out.println("Something went wrong.");
	    }
	}

	double CheckCouponCode(String userEnterCouponCode){// FOR CHECK THE COUPON IS VALID OR NOT AND RETURN THE DISCOUNT 
		int ind=findIndex(codeCouponList,userEnterCouponCode);
		if (ind==-1) return -1;
		else return(codeCouponDiscountList[ind]);
	}
}

//====================STORE CLASS END =========================//


//==================CALCULATION CLASS START ============//

class Calculations{// FOR DIFFERENT TYPE OF CALCULATIONS
	double PriceWithDiscount(double price,double discount){// FOR CALCULATIONG THE PRICE WITH APPLY THE DISCOUNT
		double result;
		result=(price*discount)/100;
		return (price-result);
	}
}

//==================CALCULATION CLASS END ============//


//==================CUSTOMER CLASS START ============//
class Customer{
	Scanner in = new Scanner(System.in);
	Integer cartItems[]={}; // TO STORE CUSTOMER CHOOSEN ITEM AND ADD TO CART
	int b=0;


	void AddProductToCart(int id,Store Store1){// ADD PRODUCT TO THE CART 
		for (AddCloth emp : Store1.clothList){
			b=0;
			if (emp.id==id){
				b=1;
				if (emp.instock<=0){
					System.out.println("NOT IN STOCK STOCK OUT");
					break;
				}
				else{
					System.out.println("Product id "+id+" added to cart");
					ArrayList<Integer> myList = new ArrayList<Integer>(Arrays.asList(cartItems));
					myList.add(id);
					cartItems = myList.toArray(cartItems);
					UpdateDatabase(Store1);
					// for (int emp : cartItems){
					// 	System.out.println(emp);
					// }
					break;
				}
			}
		}
		if(b==0){System.out.println("NOT FOUND IN OUR STORE");}
	}




	void RemoveFromCart(int id,Store Store1){// REMOVE PRODUCT FROM THE CART
		int b=0;
		if(cartItems.length==0){
			System.out.println("\nAdd some product in your cart first");
		}
		else{
			
			for (int i=0;i<cartItems.length;i++){
				if(cartItems[i]==id){
					b=1;
					cartItems[i]=-9999;
					break;
				}
			}
			if(b==1){System.out.println("Product id "+id+" Remove from cart");}
			else{System.out.println("Product id "+id+" No found in cart");}
		}
	}

	void UpdateDatabase(Store Store1){
		// System.out.println(Arrays.toString(cartItems));
		for (int e : cartItems){
			if (e>-1){
				for (AddCloth emp : Store1.clothList){
					if(emp.id==e){
						emp.instock-=1;
					}
				}
			}
		}
	}


	void DisplayCartItems(Store Store1){// DISPLAY THE CART PRODUCT AND CHECK OUT FROM HERE
		Calculations Calculations1=new Calculations();
		double totalAmount=0;
		if(cartItems.length==0){
			System.out.println("\nAdd some product in your cart first");
		}
		else{
			System.out.println("\n\tNO\tTYPE\tCOLOR\tSIZE\tPRICE\tDISCOUNT  FINAL PRICE\n");
			for (int e : cartItems){
				if (e>-1){
					// System.out.println(e);
					for (AddCloth emp : Store1.clothList){
						if (emp.id==e){
							// System.out.println(emp.price);
							totalAmount+=Calculations1.PriceWithDiscount(emp.price,emp.discount);
            				System.out.println("\t"+ emp.id+"\t"+emp.clothtype+"\t"+emp.color+"\t"+emp.clothsize+"\t"+emp.price+"\t"+emp.discount+"%"+"\t  "+Calculations1.PriceWithDiscount(emp.price,emp.discount));	
							break;
						}
					}
				}
			}
			System.out.println("\nYour Total amount Is :- "+totalAmount);
			while(1==1){
				System.out.print("\nDo you want to checkout(1/0):");
				try{
					int checkOutSts=in.nextInt();//CHECK THE CHECKOUT STATUS
				
					if (checkOutSts==0){System.out.print("\nOkey..Continue your shoping");break;}//NO 
					else if (checkOutSts==1){//YES
						System.out.print("\nIf You Have any code Enter here(Else enter 0):");//CHECK THE COUPON CODE  ATANU DUTTA
						String checkCode=in.next();
						checkCode=checkCode.toUpperCase();
						double y=Store1.CheckCouponCode(checkCode);
						if (y==-1){//CHECKOUT WITHOUT COUPONCODE
							System.out.println("\nINVALID COUPON CODE");
							System.out.println("\nYour Total amount:- "+totalAmount);
							System.out.println("\nTHANKYOU FOR SHOPING WITH US HOPE WE WILL SEE YOU AGAIN");
							UpdateDatabase(Store1);
						}
						else{//CHECKOUT WITH COUPONCODE
							System.out.println(checkCode+" APPALIED");
							System.out.println("\nCongrats you got additional "+y+"% off");
							totalAmount=Calculations1.PriceWithDiscount(totalAmount,y);
							System.out.println("\nYour Total amount after apply coupon code:- "+totalAmount);
							System.out.println("\nTHANKYOU FOR SHOPING WITH US HOPE WE WILL SEE YOU AGAIN");
							UpdateDatabase(Store1);
						}
						break;
					}
					else{
						System.out.print("\nWrong Option Choose 1/0");
					}
				}
				catch(Exception e){
					System.out.print("\nINVALID");
					break;
				}
			}

		}
	}
}


//==================CUSTOMER  CLASS END ============//




//======================END=================//


// CREATED BY ATANU DUTTA 