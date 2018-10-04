package toolbox;

import java.io.Serializable;

/**
 * Created by I Love Eslam on 5/4/2017.
 */
public class Point implements Serializable{
    public float x, y, z;

    Point() {
        x = 0;
        y = 0;
        z = 0;
    }
    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getTeta(Point ac) {
        Point ab = this;
        float t = ac.dotPro(ab) / ac.value() / ab.value();
        t = Math.min(t, 1);
        t = Math.max(t, -1);
        return (float) Math.acos(t);
    }

    public Point copy(){
        Point res = new Point(x,y,z);
        return res;
    }

    public Point normal(){
        if(this.value() < 1e-3)
            return new Point();
        return this.mult(1.0f/this.value());
    }

    @Override
    public String toString(){
        return x+" "+y+" "+z;
    }

    public float dotPro(Point w) {
        return x * w.x + y * w.y + z * w.z;
    }

    public float getXYTeta() {
        return (float) Math.atan2(y, x);
    }

    public float value() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public static void addToArray(float a[], int in, Point z) {
        a[in++] = z.x;
        a[in++] = z.y;
        a[in] = z.z;
    }

    public float dist(Point t) {
        float r = x - t.x;
        r *= r;
        float r1 = y - t.y;
        r1 *= r1;
        float r2 = z - t.z;
        r2 *= r2;
        return (float) Math.sqrt(r1 + r2 + r);
    }

    public float distYZ(Point t) {
        float r1 = y - t.y;
        r1 *= r1;
        float r2 = z - t.z;
        r2 *= r2;
        return (float) Math.sqrt(r1 + r2);
    }

    public float distXZ(Point t) {
        float r = x - t.x;
        r *= r;
        float r2 = z - t.z;
        r2 *= r2;
        return (float) Math.sqrt(r2 + r);
    }

    public float distXY(Point t) {
        float r = x - t.x;
        r *= r;
        float r1 = y - t.y;
        r1 *= r1;
        return (float) Math.sqrt(r1 + r);
    }

    public Point rotateXY(Point c, float teta) {
        Point res = new Point();
        res.x = (float) ((x - c.x) * Math.cos(teta) - (y - c.y) * Math.sin(teta)) + c.x;
        res.y = (float) ((x - c.x) * Math.sin(teta) + (y - c.y) * Math.cos(teta)) + c.y;
        res.z = z;
        return res;
    }

    public Point rotateXZ(Point c, float teta) {
        Point res = new Point();
        res.x = (float) ((x - c.x) * Math.cos(teta) - (z - c.z) * Math.sin(teta)) + c.x;
        res.z = (float) ((x - c.x) * Math.sin(teta) + (z - c.z) * Math.cos(teta)) + c.z;
        res.y = y;
        return res;
    }

    public Point rotateYZ(Point c, float teta) {
        float d = this.distYZ(c);
        if (d <= 1e-3)
            return new Point(x, y, z);
        float teta1 = (float) Math.acos((y - c.y) / d);
        Point res = new Point();
        res.y = c.y + (float) Math.cos(teta + teta1) * d;
        res.z = c.z + (float) Math.sin(teta + teta1) * d;
        res.x = x;
        return res;
    }

    public static float convertToRad(float teta) {
        return (float) (teta * Math.PI / 180);
    }

    public Point add(Point t) {
        return new Point(x + t.x, y + t.y, z + t.z);
    }

    public Point minis(Point t) {
        return new Point(x - t.x, y - t.y, z - t.z);
    }

    public Point mult(float t) {
        return new Point(x * t, y * t, z * t);
    }

    public Point crossPro(Point w){
        return new Point(y*w.z-z*w.y,-x*w.z+z*w.x,x*w.y-y*w.x);
    }
    /*
        هاد class كنت عامله
    فيه اعمليات على النقاط
    انت بتسوي
    Point pt = new Point(x,y,z);
    x,y,z
    احداثياتك
    pt = pt .rotate1(start,vector,teta);
    انتبه لل 1
    لانه في تابع بدون 1 هاد لشي تاني والله اعلم
    start
    بداية الشعاع
    يلي بدك تدور حوله
    vector
    هو نقطة البداية - نقطة النهاية
    teta الزاويةوالله اعلم ب ال rad
    من شان تجيب ال vector
    Point vector = end.minis(start);
    start , end
    بتعرفهم مثل ال pt
    تمام هيك؟
     */
    public Point rotate1(Point a,Point vec,float teta){
        vec = vec.normal();
        Point res = new Point();
        res.x = a.x*(vec.y*vec.y+vec.z*vec.z) - vec.x*(a.y*vec.y + a.z*vec.z - vec.x*x - y*vec.y - z*vec.z);
        res.x *= (1f-(float)Math.cos(teta));
        res.x += x*(float)Math.cos(teta);
        res.x += (float)Math.sin(teta)*(-a.z*vec.y + a.y*vec.z - vec.z*y + vec.y*z);

        res.y = a.y*(vec.x*vec.x+vec.z*vec.z) - vec.y*(a.x*vec.x + a.z*vec.z - vec.x*x - y*vec.y - z*vec.z);
        res.y *= (1f-(float)Math.cos(teta));
        res.y += y*(float)Math.cos(teta);
        res.y += (float)Math.sin(teta)*(+a.z*vec.x - a.x*vec.z + vec.z*x - vec.x*z);

        res.z = a.z*(vec.y*vec.y+vec.x*vec.x) - vec.z*(a.x*vec.x + a.y*vec.y - vec.x*x - y*vec.y - z*vec.z);
        res.z *= (1f-(float)Math.cos(teta));
        res.z += z*(float)Math.cos(teta);
        res.z += (float)Math.sin(teta)*(-a.y*vec.x + a.x*vec.y - vec.y*x + vec.x*y);

        return res;
    }
}