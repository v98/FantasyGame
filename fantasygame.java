import java.util.*;
import java.io.*;

class creature{                             
	protected String name;
	protected int power;
	protected int health;
	protected int cost;
	protected int asset;

	public void setName(String name){
		this.name=name;

	}

	public void setPower(int p){
		this.power=p;
	}

	public void setHealth(int h){
		this.health=h;
	}

	public void setCost(int c){
		this.cost=c;
	}

	public void setAsset(int a){
		this.asset=a;
	}
	public int attack(){
		//random function generating 
		Random rand = new Random();
		int damage=rand.nextInt(power+1);//0 and power inclusive
		System.out.println("damage by creature: "+damage);
		return damage;
	}

	public void updateH(int attack){
		this.health-=attack;

	}      //creature class
	public int getH(){
		return this.health;
	}

	public int getA(){
		return this.asset;
	}

	public String getN(){
		return this.name;
	}
}

class humans extends creature{
	public humans(String name,int cost,int asset,int power,int health){
		setName(name);
		setCost(cost);
		setAsset(asset);
		setPower(power);
		setHealth(health);
	}


}

class wolf extends creature{
	public wolf(String name,int cost,int asset,int power,int health){
		setName(name);
		setCost(cost);
		setAsset(asset);
		setPower(power);
		setHealth(health);
	}	

}


class dragons extends creature{
	@Override
	public int attack(){
		int damage=super.attack();
		Random rand = new Random();
		int prob=rand.nextInt(20)+1;
		if (prob<=3){
			damage=damage+25;	
		}
		System.out.println("damage by dragon: "+damage);
		return damage;
		
	}
}
class fdragon extends dragons{
	public fdragon(String name,int cost,int asset,int power,int health){
		setName(name);
		setCost(cost);
		setAsset(asset);
		setPower(power);
		setHealth(health);
	}

}

class idragon extends dragons{
	public idragon(String name,int cost,int asset,int power,int health){
		setName(name);
		setCost(cost);
		setAsset(asset);
		setPower(power);
		setHealth(health);
	}
	@Override
	public int attack(){
		Random rand = new Random();
		int damage=super.attack();
		int prob=rand.nextInt(20)+1;
		if (prob==1){
			damage=damage+rand.nextInt(power);
		}
		System.out.println("damage by ice dragon: "+damage);
		return damage;
	}
}

class daemon extends creature{
	public daemon(String name,int cost,int asset,int power,int health){
		setName(name);
		setCost(cost);
		setAsset(asset);
		setPower(power);
		setHealth(health);
	}
	@Override
	public int attack(){
		Random rand = new Random();
		int damage=super.attack();
		int prob=rand.nextInt(2)+1;
		if(prob==1){
			damage*=2;
		}
		return damage;
	}
}



// interface team{

// 	public void addmoney(int n); //add money

// 	public void cutmoney(int n); //deduct money

// 	public int dispmoney(); //returns current money team has

// 	public void brochuremenu()throws IOException; //menuselection for creature of the team

// 	public void buyCreature()throws IOException; //buying creature

// 	public int hasplayers(); // returns no. of players alive

// 	public void killplayers(); //updates no. of players alive 

// 	public creature chooseplayers()throws IOException; //returns the creature in arena

// 	public boolean canBuy(); //capable of purchase 
// }

class teamGood{
	
	protected int money;   //money team has
	protected creature[] playerlist; //list of creature in the team
	protected String[] namelist;  //corresponding list of names 
	protected int[] alive_list; //dead or alive
	protected int n_players; // initial no. of players
	protected int basemoney;  //minimum money for purchase
	protected int[] humanData; // data of human
	protected int[] firedragonData; //data of fire dragon
	protected int[] wolfData; // data of wolf
	protected int n_alive; // no. of players alive 

	public teamGood(int mon,int[] hdata,int[] fddata,int[] wdata ){
		this.humanData=new int[4];
		this.firedragonData=new int[4];
		this.wolfData=new int[4];
		
		for(int i=0;i<4;i++){
		//	System.out.println(hdata[i]);
			humanData[i]=hdata[i];
			firedragonData[i]=fddata[i];
			wolfData[i]=wdata[i];
		}
		this.alive_list=new int[10000];
		this.playerlist=new  creature[10000];
		this.namelist=new String[10000];
		this.money=mon;
		this.basemoney=Math.min(humanData[0],Math.min(firedragonData[0],wolfData[0]));

	}

	public int dispmoney(){
		return money;
	}

	public void addmoney(int n){
		money+=n;
	}

	public void cutmoney(int n){
		money-=n;
	}

	public int hasplayers(){
		return n_alive;
	}

	public void killplayers(){
		n_alive-=1;
	}

	public void brochuremenu()throws IOException{

		if (this.canBuy())  //check if person has enough money to buy creature
		{
		//System.out.println("Money"+money);
		System.out.println("Select creaturs for Team Good \n\t1.Human\n\t2.Fire Dragon\n\t3.Wolf\n\t4.Exit Selection");
		BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(rd.readLine());
		buyCreature(n); 
		}
	}

	public void buyCreature(int n)throws IOException{
		
		if (n==1){   //human
			
			BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
			if (money<humanData[0]){
				System.out.println("Not enough money choose someone else");
				brochuremenu();
			}

			else{
				
				System.out.println("Enter the name of the Human");
				String nam=rd.readLine();
				 
				money-=humanData[0]; //complete purchase

				namelist[n_players]=nam; //update names list
				playerlist[n_players]=new humans(nam,humanData[0],humanData[1],humanData[2],humanData[3]);
				alive_list[n_players]=1;

				n_alive+=1;  //update no. pf players alive
				n_players+=1; //update index 

				brochuremenu();
			}

		}

		else if(n==2){
			
			BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
			if (money<firedragonData[0]){
				System.out.println("Not enough money choose someone else");
				brochuremenu();
			}

			else{

				System.out.println("Enter the name of the Fire Dragon");
				String nam=rd.readLine();
				
				money-=firedragonData[0];

				namelist[n_players]=nam;
				playerlist[n_players]=new fdragon(nam,firedragonData[0],firedragonData[1],firedragonData[2],firedragonData[3]);
				alive_list[n_players]=1;
				
				n_alive+=1;
				n_players+=1;
				
				brochuremenu();
			}
		}

		else if(n==3){
			
			BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
			if(money<wolfData[0]){
				System.out.println("Not enough money choose someone else.");
				brochuremenu();
			}
			else{
			
				System.out.println("Enter the name of the Wolf");
				String nam=rd.readLine();

				money-=wolfData[0];
				
				namelist[n_players]=nam;
				playerlist[n_players]=new wolf(nam,wolfData[0],wolfData[1],wolfData[2],wolfData[3]);
				
				n_alive+=1;
				n_players+=1;
				
				brochuremenu();
			}

		}

		
	}

	public boolean canBuy(){
		if (this.money>this.basemoney){
			return true;
		}
		else{
			return false;
		}
	}

	public creature chooseplayers() throws IOException{
		BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter creature from Good’s team to fight in the war-");
		String nm=rd.readLine();
		
		for(int i=0;i<n_players;i++){
			if (namelist[i].equals(nm)){
				
				alive_list[i]=0;
				return playerlist[i];

			}
		}
		return null;
	}
}

class teamBad {
	protected int money;
	protected creature[] playerlist;
	protected String[] namelist;
	protected int[] alive_list;
	protected int n_players;
	protected int n_alive;
	protected int basemoney;
	protected int[] daemonData;
	protected int[] icedragonData;

	public teamBad(int mon,int[] ddata,int[] iddata){
		this.daemonData=new int[4];
		this.icedragonData=new int[4];
		for(int i=0;i<4;i++){
			daemonData[i]=ddata[i];
			icedragonData[i]=iddata[i];

		}
		this.alive_list=new int[10000];
		this.playerlist=new  creature[10000];
		this.namelist=new String[10000];
		this.money=mon;
		this.basemoney=Math.min(daemonData[0],icedragonData[0]);

	}

	public int hasplayers(){
		return n_alive;
	}

	public void killplayers(){
		n_alive-=1;
	}
	public int dispmoney(){
		return money;
	}

	public void addmoney(int n){
		money+=n;
	}

	public void cutmoney(int n){
		money-=n;
	}

	public void brochuremenu()throws IOException{

		if (this.canBuy())
		{
		System.out.println("Select creaturs for Team Bad \n\t1.Daemon\n\t2.Ice Dragon\n\t3.Exit Selection");
		BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(rd.readLine());
		buyCreature(n);
		}
	}

	public void buyCreature(int n)throws IOException{
		if (n==1){
			
			BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
			if (money<daemonData[0]){
				System.out.println("Not enough money choose someone else.");
				brochuremenu();
			}
			else{
				System.out.println("Enter the name of the Daemon");
				String nam=rd.readLine();
				
				money-=daemonData[0];

				namelist[n_players]=nam;  //update in list of names of creature 
				alive_list[n_players]=1;  // mark as alive in alive list
				playerlist[n_players]=new daemon(nam,daemonData[0],daemonData[1],daemonData[2],daemonData[3]); //store on object array
				
				n_players+=1; //update total no. of players
				n_alive+=1 ;//update total no. of players alive 
				
				brochuremenu();
			}

		}

		else if(n==2){
			
			BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
			if (money<icedragonData[0]){
				System.out.println("Not enough money choose someone else.");
				brochuremenu();
			}
			else{

				System.out.println("Enter the name of the Ice Dragon");
				String nam=rd.readLine();

				money-=icedragonData[0];

				namelist[n_players]=nam;
				alive_list[n_players]=1;
				playerlist[n_players]=new idragon(nam,icedragonData[0],icedragonData[1],icedragonData[2],icedragonData[3]);
				
				n_players+=1;
				n_alive+=1;
				brochuremenu();
			}

		}
	}
	public boolean canBuy(){
		if (this.money>this.basemoney){
			return true;
		}
		else{
			return false;
		}
	}
	public creature chooseplayers()throws IOException{
		BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter creature from Bad’s team to fight in the war-");
		String nm=rd.readLine();
		
		for(int i=0;i<n_players;i++){
			if (namelist[i].equals(nm)){
				
				alive_list[i]=0;
				return playerlist[i];
			}
		}
		return null;
	}
}

class game{
	protected static int ngame; //round no.
	protected teamGood goodteam;  
	protected teamBad badteam;
	protected creature currG;
	protected creature currB;

	public game(teamBad t2,teamGood t1){
		ngame+=1;
		goodteam=t1;
		badteam=t2;
		System.out.println("The War Begins-");
	}

	public void beginround()throws IOException{

	 	

	 	while(goodteam.hasplayers()!=0 && badteam.hasplayers()!=0)
		{
			System.out.println("Round-"+ngame+":");
			if (currG==null || currG.getH()<=0){
				currG=goodteam.chooseplayers();
			}

			if (currB==null || currB.getH()<=0){
				currB=badteam.chooseplayers();
			}

			currG.updateH(currB.attack());
			currB.updateH(currG.attack());
			
			if (currG.getH()>0 && currB.getH()>0){
				System.out.println("Match indeterminate ,players proceed to next round");
				System.out.println("Money of Good's team is-"+goodteam.dispmoney());
				System.out.println("Money of Bad's team is-"+badteam.dispmoney());
				System.out.println("Health of Good's team is-"+currG.getH());
				System.out.println("Health of Bad's team is-"+currB.getH());
				ngame++;
				beginround();
			}
			
			else if(currG.getH()<=0 && currB.getH()>0){
				
				goodteam.killplayers();
				badteam.addmoney(currB.getA());
				
				System.out.println(currG.getN()+" loses In Round-"+ngame);
				System.out.println("Money of Good's team is-"+goodteam.dispmoney());
				System.out.println("Money of Bad's team is-"+badteam.dispmoney());
				System.out.println("Health of Good's team is-"+currG.getH());
				System.out.println("Health of Bad's team is-"+currB.getH());
				
				ngame++;
				
				if(badteam.canBuy()){
					badteam.brochuremenu();
				}

				if(goodteam.canBuy()){
					goodteam.brochuremenu();
				}

				beginround();

			}

			else if(currB.getH()<=0 && currG.getH()>0){
				badteam.killplayers();
				goodteam.addmoney(currG.getA());
				
				System.out.println(currB.getN()+" loses In Round-"+ngame);
				System.out.println("Money of Good's team is-"+goodteam.dispmoney());
				System.out.println("Money of Bad's team is-"+badteam.dispmoney());
				System.out.println("Health of Good's team is-"+currG.getH());
				System.out.println("Health of Bad's team is-"+currB.getH());
				
				ngame++;
				
				if(goodteam.canBuy()){
					goodteam.brochuremenu();
				}
				if(badteam.canBuy()){
					badteam.brochuremenu();
				}

				beginround();
			}

			else if(currG.getH()<=0 && currG.getH()<=0){
				
				goodteam.killplayers();
				badteam.killplayers();
				System.out.println("Match indeterminate.Both creatures eliminated.");
				System.out.println("Money of Good's team is-"+goodteam.dispmoney());
				System.out.println("Money of Bad's team is-"+badteam.dispmoney());
				System.out.println("Health of Good's team is-"+currG.getH());
				System.out.println("Health of Bad's team is-"+currB.getH());

				
				if(goodteam.canBuy()){
					goodteam.brochuremenu();
				}
				if(badteam.canBuy()){
					badteam.brochuremenu();
				}
				
				beginround();
			}
		}

		


	}
	public void verdict(){

		if (goodteam.hasplayers()>0 && badteam.hasplayers()<=0){
			System.out.println("Team Good wins the war.The money the team has is "+goodteam.dispmoney()+".");
		}

		else if (badteam.hasplayers()>0 && goodteam.hasplayers()<=0){
			System.out.println("Team Bad wins the war.The money the team has is "+badteam.dispmoney()+".");

		}

		else{
			System.out.println("Both teams have suffered heavy losses in the War. Winner indeterminate.");

		}

	}
	

}

public class fantasygame{

	public static void main(String[] args)throws IOException{
		BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter Team Good’s total money");
		int moneyG=Integer.parseInt(rd.readLine());
		
		System.out.println("Enter Team Bad’s total money");
		int moneyB=Integer.parseInt(rd.readLine());


		System.out.println("Enter cost,asset ,power and health for Human (space-separated)");
		String[] dataH=rd.readLine().split(" ");
		int[] dataHuman={Integer.parseInt(dataH[0]),Integer.parseInt(dataH[1]),Integer.parseInt(dataH[2]),Integer.parseInt(dataH[3])};

		System.out.println("Enter cost,asset ,power and health for Fire Dragon (space-separated)");
		String[] dataFD=rd.readLine().split(" ");
		int[] dataFiredragon={Integer.parseInt(dataFD[0]),Integer.parseInt(dataFD[1]),Integer.parseInt(dataFD[2]),Integer.parseInt(dataFD[3])};

		System.out.println("Enter cost,asset ,power and health for Ice Dragon (space-separated)");
		String[] dataID=rd.readLine().split(" ");
		int[] dataIcedragon={Integer.parseInt(dataID[0]),Integer.parseInt(dataID[1]),Integer.parseInt(dataID[2]),Integer.parseInt(dataID[3])};

		System.out.println("Enter cost,asset ,power and health for Daemon (space-separated)");
		String[] dataD=rd.readLine().split(" ");
		int[] dataDaemon={Integer.parseInt(dataD[0]),Integer.parseInt(dataD[1]),Integer.parseInt(dataD[2]),Integer.parseInt(dataD[3])};

		System.out.println("Enter cost,asset ,power and health for Wolf (space-separated)");
		String[] dataW=rd.readLine().split(" ");
		int[] dataWolf={Integer.parseInt(dataW[0]),Integer.parseInt(dataW[1]),Integer.parseInt(dataW[2]),Integer.parseInt(dataW[3])};


		

		teamGood playerG=new teamGood(moneyG,dataHuman,dataFiredragon,dataWolf);
		teamBad playerB=new teamBad(moneyB,dataDaemon,dataIcedragon);

		playerG.brochuremenu();
		playerB.brochuremenu();

		game gametest=new game(playerB,playerG);

		gametest.beginround();
		gametest.verdict();



	}
}