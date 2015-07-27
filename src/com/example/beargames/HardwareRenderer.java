package com.example.beargames;

import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

@SuppressLint("NewApi")
public class HardwareRenderer implements GLSurfaceView.Renderer
{
    public static MainActivity   _activity;
    final int                       _width;
	final int 						_height;
    final int                       GRAPHICS_CANVAS              = 0;
    final int                       GRAPHICS_OPENGL_DRAW_TEXTURE = 1;
   
    private HashMap<String, String> mInfo                        = null;
    private Boolean                 mProfileHardware;
    private static	String			androidId					 = null;
    private static  float           mBgRED                       = 0.0f;//(44.0f/255.0f);//(118.0f/255.0f);//0.0f;//(84.0f/255.0f);
    private static  float           mBgGREEN                     = 0.0f;//(42.0f/255.0f);//(202.0f/255.0f);//0.0f;//
    private static  float           mBgBLUE                      = 0.0f;//(39.0f/255.0f);//(000.0f/255.0f);//0.0f;//
    private static  float           mBgALPHA                     = 1.0f;


    public HardwareRenderer(MainActivity activity, int width, int height)
    {  
        _activity = activity;
        _width = width;
        _height = height;
        mInfo = null;
        mProfileHardware = false;
    }

    public void enableHardwareProfiling()
    {
        mProfileHardware = true;
    }

    public HashMap<String, String> getHardwareProfile()
    {
        if (mInfo != null) return mInfo;
        return null;
    }

    public static boolean  has_determined_support   = false;
    public int             gl_max_texture_size      = 0;
    public boolean         gl_software_renderer     = false;
    public boolean         gl_supports_draw_texture = false;
    public String          gl_graphic_engine        = null;
    public String          gl_extensions            = null;
    public String          gl_version               = null;
    public String          gl_renderer              = null;

    private String getSecureState(String item)
    {
        String value = Settings.Secure.getString(_activity.getContentResolver(), item);
        if (value != null && value.equals("1")) return "true";
        return "false";
    }

    @SuppressWarnings("deprecation")
	@SuppressLint("DefaultLocale")
	public void determineGraphicSupport(GL10 gl)
    {
        if (mInfo != null)
        {
            return;
        }
        gl_extensions = gl.glGetString(GL10.GL_EXTENSIONS);
        gl_version = gl.glGetString(GL10.GL_VERSION);
        gl_renderer = gl.glGetString(GL10.GL_RENDERER);
        gl_software_renderer = gl_renderer.toLowerCase().contains("pixelflinger");
        gl_supports_draw_texture = gl_extensions.toLowerCase().contains("draw_texture");
        int[] arGlMaxTextureSize = new int[1];
        gl.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, arGlMaxTextureSize, 0);
        
        gl_max_texture_size = arGlMaxTextureSize[0];
        
        System.out.println("gl_max_texture_size="+gl_max_texture_size);

        DisplayMetrics metrics = new DisplayMetrics();
        _activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int rWidth = (int) (metrics.widthPixels * metrics.density);
        int rHeight = (int) (metrics.heightPixels * metrics.density);

        gl_graphic_engine = "Canvas";
        if (!gl_software_renderer && gl_supports_draw_texture && rWidth >= 480 && rHeight >= 800 && arGlMaxTextureSize[0] >= 2048)
        	gl_graphic_engine = "OpenGL EOS draw texture";

        if (gl_renderer != null)
        	has_determined_support = true;

        mInfo = new HashMap<String, String>();
        // WURFL Replacements
        mInfo.put("device_os", "Android");
        mInfo.put("device_os_version", android.os.Build.VERSION.RELEASE);
        mInfo.put("brand_name", android.os.Build.BRAND);
        mInfo.put("model_name", android.os.Build.MODEL);
        mInfo.put("resolution_width", String.valueOf(metrics.widthPixels));
        mInfo.put("resolution_height", String.valueOf(metrics.heightPixels));
        // New to WURFL
        mInfo.put("gl_extensions", gl_extensions);
        if (gl_version.equals("0.0.1"))
        { // Apparently this is a Samsung software renderer
            mInfo.put("gl_version", "1.1");
            mInfo.put("gl_version_string", "OES 1.1 EGL 1.3");
        }
        else
        {
            mInfo.put("gl_version_string", gl_version);
            Pattern pattern = Pattern.compile("^.+?(?:\\s+?|-??)([0-9.]+)\\s*");
            Matcher match = pattern.matcher(gl_version);
            if (match.find())
            {
                mInfo.put("gl_version", match.group(1));
            }
        }
        mInfo.put("gl_renderer", gl_renderer);
        mInfo.put("gl_supports_draw_texture", String.valueOf(gl_supports_draw_texture));
        mInfo.put("gl_software_renderer", String.valueOf(gl_software_renderer));
        mInfo.put("gl_max_texture_size", String.valueOf(gl_max_texture_size));
        mInfo.put("gl_graphic_engine", gl_graphic_engine);
        mInfo.put("pixel_density", String.valueOf(metrics.density));
        mInfo.put("build_board", android.os.Build.BOARD);
        mInfo.put("build_cpu_abi", android.os.Build.CPU_ABI);
        mInfo.put("build_device", android.os.Build.DEVICE);
        mInfo.put("build_display", android.os.Build.DISPLAY);
        mInfo.put("build_fingerprint", android.os.Build.FINGERPRINT);
        mInfo.put("build_host", android.os.Build.HOST);
        mInfo.put("build_id", android.os.Build.ID);
        mInfo.put("build_manufacturer", android.os.Build.MANUFACTURER);
        mInfo.put("build_product", android.os.Build.PRODUCT);
        mInfo.put("build_tags", android.os.Build.TAGS);
        mInfo.put("build_time", String.valueOf(android.os.Build.TIME));
        mInfo.put("build_type", android.os.Build.TYPE);
        mInfo.put("build_user", android.os.Build.USER);
        mInfo.put("version_codename", android.os.Build.VERSION.CODENAME);
        mInfo.put("version_incremental", android.os.Build.VERSION.INCREMENTAL);
        mInfo.put("version_sdk", Integer.toString(android.os.Build.VERSION.SDK_INT));
        mInfo.put("android_id", Settings.Secure.getString(_activity.getContentResolver(), Settings.Secure.ANDROID_ID));
        mInfo.put("background_data", getSecureState(Settings.Secure.BACKGROUND_DATA));
        mInfo.put("bluetooth_on", getSecureState(Settings.Secure.BLUETOOTH_ON));
        mInfo.put("usb_mass_storage", getSecureState(Settings.Secure.USB_MASS_STORAGE_ENABLED));
        mInfo.put("wifi_on", getSecureState(Settings.Secure.WIFI_ON));
        //mInfo.put("inet_connection", _activity.hasConnectivity() ? "true" : "false");
        // extra data
        addExtraData();
        
        TelephonyManager telemgr = (TelephonyManager) _activity.getSystemService(Context.TELEPHONY_SERVICE);
        mInfo.put("IMEI_ESN", telemgr.getDeviceId());
        switch (telemgr.getPhoneType())
        {
        	case TelephonyManager.PHONE_TYPE_GSM:
        		mInfo.put("phone_type", "gsm");
        	case TelephonyManager.PHONE_TYPE_CDMA:
        		mInfo.put("phone_type", "cdma");
            break;
        	case TelephonyManager.PHONE_TYPE_NONE:
        	default:
        		mInfo.put("phone_type", "none");
            break;
        }
        try
        {
            @SuppressWarnings({ "rawtypes", "unused" })
            Class nfcManagerClass = Class.forName("android.nfc.NfcAdapter");
            mInfo.put("nfc_support", "true");
        }
        catch (ClassNotFoundException e)
        {
            mInfo.put("nfc_support", "false");
        }
        StringBuffer locale_sb = new StringBuffer();
        Locale locales[] = Locale.getAvailableLocales();
        for (int i = 0; i < locales.length; i++)
        {
            if (locale_sb.length() > 0) locale_sb.append(" ");
            locale_sb.append(locales[i].toString());
        }
        mInfo.put("available_locales", locale_sb.toString());
        mInfo.put("prefered_locale", Locale.getDefault().toString());
        mInfo.put("pointing_method", "touchscreen");
        mInfo.put("has_qwerty_keyboard", (_activity.getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS) ? "true" : "false");
        mInfo.put("is_tablet", checkIfIsTablet() ? "true" : "false");
        mInfo.put("is_wireless_device", "true");
        mInfo.put("is_gps", "true");
        mInfo.put("marketing_name", ""); // no idea where WURFL get's this from
        
        //back to main activity
        _activity.continueLoad(mInfo);
    }
    /**
     * get the android id
     * @param c
     * @return
     */
    public static String getAndroidID(Context c)
    {
    	if(c==null)
    		return null;
          String androidId = null;
          try
          {
               androidId= Secure.getString(c.getContentResolver(), Secure.ANDROID_ID);
          }
          catch(Exception e)
          {
        	  System.out.println(e.toString());
          };
          if(androidId==null)
          {
        	  return getIdForDevice(c);
          }
          HardwareRenderer.androidId = androidId;
          return androidId;
    }
    /**
     * get android Id from static variable
     * @return
     */
    public static String getAndroidID()
    {
    	return androidId;
    }
    /**
     * construct a unique ID for this android device
     * @param c
     * @return
     */
    private static String getIdForDevice(Context c)
    {
    	if(c==null)
    		return null;
		TelephonyManager TelephonyMgr = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
		String m_szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE

		//2 compute DEVICE ID
		String m_szDevIDShort = "4567" + 
		Build.BOARD.length()%10+ Build.BRAND.length()%10 + 
		Build.DEVICE.length()%10 + 
		Build.DISPLAY.length()%10 + Build.HOST.length()%10 + 
		Build.ID.length()%10 + 
		Build.MODEL.length()%10 + Build.PRODUCT.length()%10 + 
		Build.TAGS.length()%10 + Build.TYPE.length()%10 + 
		Build.USER.length()%10 ;

		//3 android ID - unreliable
		String m_szAndroidID = Secure.getString(c.getContentResolver(), Secure.ANDROID_ID); 

		String m_szWLANMAC = "";
		try 
		{
			//4 wifi manager, read MAC address - requires  android.permission.ACCESS_WIFI_STATE or comes as null
			WifiManager wm = (WifiManager)c.getSystemService(Context.WIFI_SERVICE);
			m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
		} 
		catch (Exception e)
		{
		}
		//6 SUM THE IDs
		String m_szLongID = "";
		if(m_szImei!=null)
			m_szLongID = m_szLongID + m_szImei;
		if(m_szDevIDShort!=null)
			m_szLongID = m_szLongID + m_szDevIDShort;
		if(m_szAndroidID!=null)
			m_szLongID = m_szLongID + m_szAndroidID;
		if(m_szWLANMAC!=null)
			m_szLongID = m_szLongID + m_szWLANMAC;
		
		if(m_szLongID.trim().equals(""))
		{
			return null;
		}
		else
		{
			androidId = m_szLongID;
			return m_szLongID;
		}
    }
    private Boolean checkIfIsTablet()
    {
        // if it's HONEYCOMB, assume tablet
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT <= 13) return true;
        // if the screen size is >= LARGE, assume tablet
        if ((_activity.getApplicationContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) return true;
        return false;
    }

    
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        // Set the background color to black ( rgba ).
        gl.glClearColor(mBgRED, mBgGREEN, mBgBLUE, mBgALPHA);
        // Enable Smooth Shading, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix
        gl.glLoadIdentity();
    }


    private Integer _wait_for_draw  = 10;

    private boolean debug_opengl_ran = false;
    public void onDrawFrame(GL10 gl)
    {
    	if (!debug_opengl_ran)
    	{
    	System.out.println("*** BEGIN OpenGL ES INFO ***");
    	System.out.println("GL_RENDERER: "+gl.glGetString(GL10.GL_RENDERER));
    	System.out.println("GL_VERSION: "+gl.glGetString(GL10.GL_VERSION));
    	System.out.println("GL_EXTENSIONS: "+gl.glGetString(GL10.GL_EXTENSIONS));
    	System.out.println("*** END OpenGL ES INFO ***");
    		debug_opengl_ran = true;
    	}
    	if (mProfileHardware)
        {
                determineGraphicSupport(gl);
        }
        
    }
    
    private void addExtraData()
    {
    	try
    	{
	    	 TelephonyManager telemgr = (TelephonyManager) _activity.getSystemService(Context.TELEPHONY_SERVICE);
	    	 switch(telemgr.getNetworkType())
	    	 {
		    	 case TelephonyManager.NETWORK_TYPE_1xRTT:
		    		 mInfo.put("network_type", "1xRTT");
		    	 case TelephonyManager.NETWORK_TYPE_CDMA:
		    		 mInfo.put("network_type", "cdma");
		    	 case TelephonyManager.NETWORK_TYPE_EDGE:
		    		 mInfo.put("network_type", "edge");
		    	 case TelephonyManager.NETWORK_TYPE_EVDO_0:
		    		 mInfo.put("network_type", "evdo_0");
		    	 case TelephonyManager.NETWORK_TYPE_EVDO_A:
		    		 mInfo.put("network_type", "evdo_a");
		    	 case TelephonyManager.NETWORK_TYPE_GPRS:
		    		 mInfo.put("network_type", "gprs");
		    	 case TelephonyManager.NETWORK_TYPE_UMTS:
		    		 mInfo.put("network_type", "umts");
		    	 case TelephonyManager.NETWORK_TYPE_UNKNOWN:
		    		 mInfo.put("network_type", "unknown");
	    		 break;
	    	 }
	    	 mInfo.put("network_operator", telemgr.getNetworkOperator());
	    	 mInfo.put("sim_spn", telemgr.getSimOperatorName());
	    	 mInfo.put("sim_mcc_mnc", telemgr.getSimOperator());
	    	 mInfo.put("sim_iso", telemgr.getSimCountryIso());
	    	 mInfo.put("network_npn", telemgr.getNetworkOperatorName());
	    	 
	    	 ConnectivityManager connectivityManager = (ConnectivityManager) _activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	         NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
	         
	         mInfo.put("network_subtype_readable", netInfo.getSubtypeName());
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.toString());
    	}
    }
}














