;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[][] arr={{1,2,3},{4,5,6}};
        int[][] arr2={{6,5,4},{3,2,1}};
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr2.length;j++){
                arr[i][j]+=arr2[i][j];

                System.out.println(arr[i][j]+" ");
            }
        }System.out.println();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }




}