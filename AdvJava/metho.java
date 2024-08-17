// import java.util.*;
//  class metho
//  {
//     int eid;
//     String ename;
//     float salary;

//     void getData()
//     {
//         Scanner sc = new Scanner(System.in);
//         System.out.println("Enter id, name and salary");
//         eid = sc.nextInt();
//         ename = sc.next();
//         salary = sc.nextFloat();
//     }
//     void showData()
//     {
//         System.out.println("ID:"+eid+"\nName:"+ename+"\nSalary:"+salary);
//     }
//  }
//  class programmer extends metho
//  {
//     int incentive;
//     void getData()
//     {
//         super.getData();
//         Scanner sc = new Scanner (System.in);
//         System.out.println("Enter the Incentive");
//         incentive = sc.nextInt();
//         }
//         void showData()
//         {
//             super.showData();
//             System.out.println("Incentive:" +incentive);
//         }
//         public static void main(String[] args) {
//             programmer p = new programmer();
//             p.getData();
//             System.out.println("!!!!!!!!!!!!!!Programmer Details!!!!!!!!!!!!!!!");
//             p.showData();;
//         }
//  }