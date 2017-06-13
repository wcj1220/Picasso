# Picasso

Picasso是Square公司开源的一个Android图形缓存库，地址http://square.github.io/picasso/  可以实现图片下载和缓存功能。仅仅只需要一行代码就能完全实现图片的异步加载：


Picasso.with(this).load(" http://i.imgur.com/DvpvklR.png ").into(imageView1);


Picasso不仅实现了图片异步加载的功能，还解决了android中加载图片时需要解决的一些常见问题：
  1.在adapter中需要取消已经不在视野范围的ImageView图片资源的加载，否则会导致图片错位，Picasso已经解决了这个问题。
  2.使用复杂的图片压缩转换来尽可能的减少内存消耗
  3.自带内存和硬盘二级缓存功能
   
   
Place holders-空白或者错误占位图片：picasso提供了两种占位图片，未加载完成或者加载发生错误的时需要一张图片作为提示。
        
         
    Picasso.with(this).load(url)       
    .placeholder(R.mipmap.picasso) //未加载完成
    .error(R.mipmap.ic_launcher)  //加载发生错误
    .into(imageView2);
    

