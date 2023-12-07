package com.example.demo4;

public class Camera {
    Vector VRP ;
    Vector VRV ;
    Vector VUV;
    Vector LookAt;

    public Camera(Vector VRP, Vector LookAt, Vector VRV, Vector VUV) {
        this.VRP = VRP;
        this.LookAt = LookAt;
        this.VRV = VRV;
        this.VUV = VUV;
    }
    public Vector origin(double u,double v1)
    {
        Vector VRV;
        Vector VPN = LookAt.sub(VRP);
        VPN.normalise();
        Vector VUV = new Vector(0,1,0);
        VRV = VPN.cross(VUV);
        VRV.normalise();
        VUV=VRV.cross(VPN);
        VUV.normalise();
        Vector pws = VRP;
        pws = pws.add(VRV.mul(u));
        pws = pws.add(VUV.mul(-v1));
        return pws;
    }
    public Vector returnVPN()
    {
        Vector VPN = LookAt.sub(VRP);
        VPN.normalise();
        return VPN;
    }

}
