package com.corp.k.androidos.android.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ScrollView;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by prdcv172 on 8/15/18.
 */

public class CommonUtils {
    /**
     * Function is used to validate the username
     * contain only character or number
     *
     * @param username
     * @return
     */
    public static boolean validateUserNameFormat(String username) {
        if (username == null) {
            return false;
        }
        Pattern p = Pattern.compile(Constants.PATTERM_ACCEPTED_USERNAME);
        return !p.matcher(username).find();
    }

    /**
     * Function is used to validate the password
     * contain only character or number
     *
     * @param password
     * @return
     */
    public static boolean validatePasswordFormat(String password) {
        if (password == null) {
            return false;
        }
        Pattern p = Pattern.compile(Constants.PATTERM_ACCEPTED_PASSWORD);
        return !p.matcher(password).find();
    }

    /**
     * Function is used to validate the username length
     * contain only character or number
     *
     * @param username
     * @return
     */
    public static boolean validateUserNameLength(String username) {
        if (username == null) {
            return false;
        }
        return !(username.length() < Constants.MIN_CHARACTER_USERNAME || username.length() > Constants.MAX_CHARACTER_USERNAME);

    }

    /**
     * Function is used to validate the password
     * contain only character or number
     *
     * @param password
     * @return
     */
    public static boolean validatePasswordLength(String password) {
        if (password == null) {
            return false;
        }
        return !(password.length() < Constants.MIN_CHARACTER_PASSWORD || password.length() > Constants.MAX_CHARACTER_PASSWORD);
    }

    public static String getTimeClose(float closeTime) {
        String timeClose = "";
        int hour = (int) closeTime;
        int minutes = (int) ((closeTime - hour) * 60);
        timeClose = getTwoDigitNumber(hour) + ":" + getTwoDigitNumber(minutes);
        return timeClose;
    }

    public static String getTwoDigitNumber(int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return number + "";
        }
    }

    public static boolean isConnectedNetwork(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return !(networkInfo == null || !networkInfo.isConnected());
        }
        return false;
    }

    /**
     * Expand more view with animation.
     * @param v View to expand
     * @param heightView height of view, if height = -1, it will get measure height of view
     */
    public static void expand(final View v, int heightView) {
        //set Visible
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            v.setVisibility(View.VISIBLE);
            final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            v.measure(widthSpec, heightSpec);
            ValueAnimator mAnimator;
            if(heightView<0) {
                mAnimator = slideAnimator(0, v.getMeasuredHeight(), v);
            }else {
                mAnimator = slideAnimator(0, heightView, v);
            }
            mAnimator.setDuration(200);
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                }
            });
            mAnimator.start();
        }else{
            v.setVisibility(View.VISIBLE);
        }

    }

    public static void expandView(final View v) {
        //set Visible
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            v.setVisibility(View.VISIBLE);
            final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            v.measure(widthSpec, heightSpec);
            ValueAnimator mAnimator;
            mAnimator = slideAnimator(0, v.getMeasuredHeight(), v);
            mAnimator.setDuration((long) (v.getMeasuredHeight() / v.getContext().getResources().getDisplayMetrics().density));
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                }
            });
            mAnimator.start();
        }else{
            v.setVisibility(View.VISIBLE);
        }

    }

    /**
     * Collapse less view with animation.
     * @param v
     */
    public static void collapse(final View v) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            int finalHeight = v.getHeight();
            ValueAnimator mAnimator;
            mAnimator = slideAnimator(finalHeight, 0, v);
            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    //Height=0, but it set visibility to GONE
                    v.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            mAnimator.setDuration(200);
            mAnimator.start();
        }else{
            v.setVisibility(View.GONE);
        }
    }

    public static ValueAnimator slideAnimator(int start, int end, final View v) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    /**
     * Focus to a view on scroll view.
     * @param scrollViewParent
     * @param view
     */
    public static void scrollToView(final ScrollView scrollViewParent, final View view) {
        // Get deepChild Offset
        Point childOffset = new Point();
        getDeepChildOffset(scrollViewParent, view.getParent(), view, childOffset);
        // Scroll to child.
        scrollViewParent.smoothScrollTo(0, childOffset.y);
    }

    /**
     * Get deep height of child in parent view.
     * @param mainParent
     * @param parent
     * @param child
     * @param accumulatedOffset
     */
    public static void getDeepChildOffset(final ViewGroup mainParent, final ViewParent parent, final View child, final Point accumulatedOffset) {
        ViewGroup parentGroup = (ViewGroup) parent;
        accumulatedOffset.x += child.getLeft();
        accumulatedOffset.y += child.getTop();
        if (parentGroup.equals(mainParent)) {
            return;
        }
        getDeepChildOffset(mainParent, parentGroup.getParent(), parentGroup, accumulatedOffset);
    }

    /**
     * Random a int number.
     * @param max max of range
     * @return
     */
    public static int randInt(int max) {

        long currentTime = System.currentTimeMillis() / 1000;

        String rand = String.valueOf(currentTime % max);

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = Integer.parseInt(rand);

        return randomNum;
    }

    private static boolean isShowDialog = false;
    /**
     * Show no-internet dialog using material dialog.
     * @param activity the activity that calls it.
     */
//    public static void showDialogNoInternet(Activity activity){
//        if(activity!=null && !activity.isFinishing() && !isShowDialog) {
//            isShowDialog = true;
//            new MaterialDialog.Builder(activity)
//                    .title(R.string.error_network_not_avaiable_title)
//                    .content(R.string.error_network_not_avaiable_content2)
//                    .positiveText(R.string.txt_yes)
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(MaterialDialog dialog, DialogAction which) {
//                            isShowDialog = false;
//                        }
//                    })
//                    .show();
//        }
//    }

//    public static void showDialogError(Activity activity){
//        if(activity!=null && !activity.isFinishing() && !isShowDialog) {
//            isShowDialog = true;
//            new MaterialDialog.Builder(activity)
//                    .title(R.string.error_warning)
//                    .content(R.string.error_network_not_avaiable_content2)
//                    .positiveText(R.string.txt_yes)
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(MaterialDialog dialog, DialogAction which) {
//                            isShowDialog = false;
//                        }
//                    })
//                    .show();
//        }
//    }

    /**
     * Show custom warning dialog with title and content using material dialog.
     * @param activity the activity that calls it.
     * @param title title of the dialog.
     * @param content content text of the dialog.
     */
//    public static void showDialogWarning(Activity activity, String title, String content){
//        if(activity!=null && !activity.isFinishing()) {
//            new MaterialDialog.Builder(activity)
//                    .title(title)
//                    .content(content)
//                    .positiveText(R.string.txt_yes)
//                    .show();
//        }
//    }

    /**
     * Convert a number string to a currency number string
     * @param number number
     * @param typeMoney format currency.
     * @return
     */
    public static String getFormatCurrencyString(String number, String typeMoney){
//        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
//        symbols.setDecimalSeparator(',');
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###.##", symbols);
        return String.format(typeMoney,getDecimalFormattedString(number));
    }

    /**
     * parse a string of date to a new symbolDate
     * @param date date in string
     * @param symbolDate pattern of formatting date.
     * @return
     */
    public static String parseDateString(String date, String symbolDate) {
        if(date==null){
            return "";
        }
        String dateString;
        if(date.contains("T")) {
            date = date.trim().replace("T", " ");
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date newDate = format.parse(date);
                format = new SimpleDateFormat(symbolDate);
                dateString = format.format(newDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return " ";
            }
        }else{
            dateString = date.trim().replace("T", "\n").replace("-", "/");
        }
        return dateString;
    }

    /**
     *  parse string value of number to currency format.
     * @param value
     * @return
     */
    public static String getDecimalFormattedString(String value)
    {
        if(value==null || value.isEmpty()) return "0";
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1)
        {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt( -1 + str1.length()) == '.')
        {
            j--;
            str3 = ".";
        }
        for (int k = j;; k--)
        {
            if (k < 0)
            {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                if(str3.length()>2 && str3.startsWith("-")&& String.valueOf(str3.charAt(1)).equals(",")){
                    StringBuilder sb = new StringBuilder(str3);
                    str3 = sb.deleteCharAt(1).toString();
                }
                return str3;
            }
            if (i == 3)
            {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }

    }

    /**
     * remove Comma character in a string
     * @param string
     * @return
     */
    public static String trimCommaOfString(String string) {
//        String returnString;
        if(string.contains(",")){
            return string.replace(",","");}
        else {
            return string;
        }
    }

    /**
     * Get size of bitmap
     * @param data bitmap to get size
     * @return
     */
    public static int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else if (Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT){
            return data.getByteCount();
        } else{
            return data.getAllocationByteCount();
        }
    }

    /**
     *  Get content size of Uri
     * @param context
     * @param uri
     * @return
     */
    public static long getContentSizeFromUri(Context context, Uri uri) {
        long contentSize = 0;
        String[] proj = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE };
        CursorLoader cursorLoader = new CursorLoader(context, uri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if(cursor != null)
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
            if (cursor.moveToFirst() ) contentSize = cursor.getLong(column_index);
            cursor.close();
        }
        return contentSize;
    }

    /**
     * checking the package name is exists or not
     * @param packagename
     * @param packageManager
     * @return
     */
    public static boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Clear view when destroy
     * @param view
     */
    public static void unbindDrawables(View view) {
        if (view != null) {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }

        }
    }

    /**
     * Clear cache Picasso
     */
    private static final String PICASSO_CACHE = "picasso-cache";

    public static void clearCache(Context context) {
        final File cache = new File(
                context.getApplicationContext().getCacheDir(),
                PICASSO_CACHE);
        if (cache.exists()) {
            deleteFolder(cache);
        }
    }

    private static void deleteFolder(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles())
                deleteFolder(child);
        }
        fileOrDirectory.delete();
    }

    /**
     * Convert date to japanese date character
     * @param completeDate
     * @return
     */
    public static String getDayInWeek(String completeDate) {
        if(completeDate==null){
            return "";
        }
        String dateString = "";
        if(completeDate.contains("T")) {
            completeDate = completeDate.trim().replace("T", " ");
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date newDate = format.parse(completeDate);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                String dayOfTheWeek = sdf.format(newDate);
                switch (dayOfTheWeek){
                    case "Monday":
                        dayOfTheWeek = "月";
                        break;
                    case "Tuesday":
                        dayOfTheWeek = "火";
                        break;
                    case "Wednesday":
                        dayOfTheWeek = "水";
                        break;
                    case "Thursday":
                        dayOfTheWeek = "木";
                        break;
                    case "Friday":
                        dayOfTheWeek = "金";
                        break;
                    case "Saturday":
                        dayOfTheWeek = "土";
                        break;
                    default:
                        dayOfTheWeek = "日";
                        break;
                }
                return "("+dayOfTheWeek+")";
            } catch (ParseException e) {
                e.printStackTrace();
                return " ";
            }
        }
        return dateString;
    }

    /**
     * Check activity for intent.
     * @param context
     * @param intent
     * @return
     */
    public static boolean isActivityForIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    public static void goToAppInStore(Context context) {
        String packageName = context.getPackageName();
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        }
        catch (android.content.ActivityNotFoundException anfe) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + packageName));
            context.startActivity(intent);
        }
    }


    public static double checkPercentAvail(Activity mActivity){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) mActivity.getSystemService(Activity.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / 0x100000L;

        //Percentage can be calculated for API 16+
        return mi.availMem / (double)mi.totalMem;
    }
}
