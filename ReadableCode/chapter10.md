# 無関係の下位問題を抽出する
  
大きな問題から、小さな問題を抽出してメソッド化するということ  
ライブラリにあるような汎用的な問題、複数のコントローラーで使うApplicationControllerに書くような問題は特に積極的にメソッド化すべき（DRY)  
  
その部分のユニットテストによってデバッグもしやすくなるメリットもある  

```
// 与えられた緯度経度に最も近い'array'の要素を返す。
// 地球が完全な球体であることを前提としている。
var findClosestLocation = function (lat, lng, array) {
    var closest;
    var closest_dist = Number.MAX_VALUE;
    for (var i = 0; i < array.length; i += 1) {
        // 2つの地点をラジアンに変換する。
        var lat_rad = radians(lat);
        var lng_rad = radians(lng);
        var lat2_rad = radians(array[i].latitude);
        var lng2_rad = radians(array[i].longitude);

        // 「球面三角法の第二余弦定理」の公式を使う。
        var dist = Math.acos(Math.sin(lat_rad) * Math.sin(lat2_rad) +
                             Math.cos(lat_rad) * Math.cos(lat2_rad) *
                             Math.cos(lng2_rad - lng_rad));
        if (dist < closest_dist) {
            closest = array[i];
            closest_dist = dist;
        }
    }
    return closest;
};
```

->

```
var findClosestLocation = function (lat, lng, array) {
    var closest;
    var closest_dist = Number.MAX_VALUE;
    for (var i = 0; i < array.length; i += 1) {

	// 分かりにくい計算の中身を一行で代替出来る
        var dist = spherical_distance(lat, lng, array[i].latitude, array[i].longitude);

        if (dist < closest_dist) {
            closest = array[i];
            closest_dist = dist;
        }
    }
    return closest;
};

// この部分だけでのユニットテストも出来るようになる
var spherical_distance = function (lat1, lng1, lat2, lng2) {
    var lat1_rad = radians(lat1);
    var lng1_rad = radians(lng1);
    var lat2_rad = radians(lat2);
    var lng2_rad = radians(lng2);
    // 「球面三角法の第二余弦定理」の公式を使う。
    return Math.acos(Math.sin(lat1_rad) * Math.sin(lat2_rad) +
                     Math.cos(lat1_rad) * Math.cos(lat2_rad) *
                     Math.cos(lng2_rad - lng1_rad));
};
```
