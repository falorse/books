# 一度に一つのことを
  
コードは一つずつタスクを行えるようにする  
以下のように分解する    

	1. コードが行っている「タスク」を列挙する  
	2. 「タスク」を一つ一つの簡易な関数、領域に分割する  
  
例えば、標準偏差を計算したい場合  

	1. 平均値を計算  
	2. 分散を計算  
	3. 標準偏差を計算  

となる   
    

```
// 標準偏差を計算したい
public double calcStandatdDiviation(int[] data){
        double sum = 0;
        double vars = 0;
        for(int i=0; i<data.length; i++ ) {
            sum += data[i];
        }
        double ave = ( (double)sum )/data.length;
        for (int i=0; i<data.length; i++) {
            vars += ((data[i] - ave)*(data[i] - ave));
        }
       double std = Math.sqrt(vars/data.length);
       return std;
}
```

->  

```
public double calcStandatdDiviation(int[] data){

        double ave = calcAverage(data);
	
	double vars = calcVariance(data, ave);

       double std = Math.sqrt(vars/data.length);
 
      return std;
}
```
