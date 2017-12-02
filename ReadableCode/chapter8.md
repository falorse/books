# 巨大な式を分割する
コードの複雑な式を簡略化する

## 説明変数、要約変数
  
1. 大きなコードの塊  
2. 一目では分かりにくい塊  
3. 何回か使う塊  (DRYの原則からも大事)  
  
は、分かりやすい名前の変数に置き換える  

```
if (line.split(':')[0].strip() == "root"){
	//
}
```

->

```
username = line.split(':')[0].strip()
if username == "root"
	//
```
  
```
if(request.user.id == document.owner_id){
	//
}else{
	//
}
```

->

```
boolean user_own_document = (request.user.id == document.owner.id)
if(user_own_document){
	//
}

if(!user_own_document){
	//
}
```


## ド・モルガンの法則
  
論理式は簡潔に書くための工夫  

```
!(A && !B)
!A || B
は等価
```

論理式の中の　! や　() は極力減らしていく  
でもunless とか if not は個人的には嫌い  

## メソッド化
  
• メインの処理でない場合  
• 複雑な条件処理  
• 道中は複雑だけど結果は単純な処理 
  
などはメソッドに切り出せるかも  
メソッドに切り出す際は、分かりやすい名前にしておく  

```
if( int(status/100) == 4 || int(status/100) == 5 || err != null) {
	//
} 
```

->

```
if( hasError()){
	//
}
```
