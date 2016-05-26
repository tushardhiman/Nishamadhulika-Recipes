package info.androidhive.materialdesign.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.widget.ImageView;

import info.androidhive.materialdesign.R;


public class AvatarDownloader {
    
    //the simplest in-memory cache implementation. This should be replaced with something like SoftReference or BitmapOptions.inPurgeable(since 1.6)
	 //private HashMap<string bitmap=""> cache=new HashMap<string bitmap="">();
	 private HashMap<String, Bitmap> cache=new HashMap<String, Bitmap>();
     
    private File cacheDir;
     
    public AvatarDownloader(Context context){
        //Make the background thread low priority. This way it will not affect the UI performance
        photoLoaderThread.setPriority(Thread.NORM_PRIORITY-1);
         
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"/download/myApp/avatars/");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
        
    }
     
    final int stub_id= R.drawable.ic_profile;
    public void DisplayImage(String url, String profilePic, Activity activity, ImageView imageView)
    {
        if(cache.containsKey(url))
            imageView.setImageBitmap(cache.get(url));
        else
        {
        
            queuePhoto(url, activity, imageView, profilePic);
            imageView.setImageResource(stub_id);
        }    
    }
         
    private void queuePhoto(String url, Activity activity, ImageView imageView, String profilePic)
    {
        //This ImageView may be used for other images before. So there may be some old tasks in the queue. We need to discard them. 
        photosQueue.Clean(imageView);
        System.err.println("QPH"+url);
        PhotoToLoad p=new PhotoToLoad(url, imageView, profilePic);
        synchronized(photosQueue.photosToLoad){
            photosQueue.photosToLoad.push(p);
            photosQueue.photosToLoad.notifyAll();
        }
         
        //start thread if it's not started yet
        if(photoLoaderThread.getState()==Thread.State.NEW)
            photoLoaderThread.start();
    }
     
    private Bitmap getBitmap(String url, String profilePic) 
    {
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        //String filename=String.valueOf(url.hashCode());
        //File f=new File(cacheDir, filename);
      
        File f=new File(cacheDir, profilePic);
         
        //from SD cache
        Bitmap b = decodeFile(f);
        if(b!=null)
            return b;
         
        //from web
        try {
            System.err.println("WEB:"+url);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            return bitmap;
        } catch (Exception ex){
           ex.printStackTrace();
           return null;
        }
    }
 
    //decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f){
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);
             
            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE=70;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }
             
            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
     
    //Task for the queue
    private class PhotoToLoad
    {
        public String url;
        public ImageView imageView;
        public String profilePic;
        public PhotoToLoad(String u, ImageView i, String profilePic){
            this.url   = u; 
            this.imageView  = i;
            this.profilePic = profilePic;
        }
    }
     
    PhotosQueue photosQueue=new PhotosQueue();
     
    public void stopThread()
    {
        photoLoaderThread.interrupt();
    }
     
    //stores list of photos to download
    public class PhotosQueue
    {
        private Stack<PhotoToLoad> photosToLoad=new Stack<PhotoToLoad>();
         
        //removes all instances of this ImageView
        public void Clean(ImageView image)
        {
            for(int j=0 ;j<photosToLoad.size();){
                if(photosToLoad.get(j).imageView==image)
                    photosToLoad.remove(j);
                else
                    ++j;
            }
        }
    }
     
    class PhotosLoader extends Thread {
        public void run() {
        	Looper.prepare();
            try {
                while(true)
                {
                	System.out.println("AvatarDownloader.PhotosLoader.run()");
                    //thread waits until there are any images to load in the queue
                    if(photosQueue.photosToLoad.size()==0)
                        synchronized(photosQueue.photosToLoad){
                            photosQueue.photosToLoad.wait();
                        }
                    if(photosQueue.photosToLoad.size()!=0)
                    {
                        PhotoToLoad photoToLoad;
                        synchronized(photosQueue.photosToLoad){
                            photoToLoad=photosQueue.photosToLoad.pop();
                            
                        }
                        System.err.println("HERE");
                        Bitmap bmp=getBitmap(photoToLoad.url, photoToLoad.profilePic);
                        cache.put(photoToLoad.url, bmp);
                        Object tag=photoToLoad.imageView.getTag();
                        System.err.println("TAG"+tag);
                     //   if(tag!=null && ((String)tag).equals(photoToLoad.url)){
                            BitmapDisplayer bd=new BitmapDisplayer(bmp, photoToLoad.imageView);
                            System.err.println(bmp.toString()+"::");
                            Activity a=(Activity)photoToLoad.imageView.getContext();                          
                            
                            a.runOnUiThread(bd);
                         
                           
                        //}
                    }
                    if(Thread.interrupted())
                        break;
                }
            } catch (InterruptedException e) {
                //allow thread to exit
            }
        }
    }
     
    PhotosLoader photoLoaderThread=new PhotosLoader();
     
    //Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable
    {
        Bitmap bitmap;
        ImageView imageView;
        public BitmapDisplayer(Bitmap b, ImageView i){bitmap=b;imageView=i;}
        public void run()
        {
        	
        	
            if(bitmap!=null)
                imageView.setImageBitmap(bitmap);
            else
                imageView.setImageResource(stub_id);
        }
    }
 
    public void clearCache() {
        //clear memory cache
        cache.clear();
         
        //clear SD cache
        File[] files=cacheDir.listFiles();
        for(File f:files)
            f.delete();
    }
}