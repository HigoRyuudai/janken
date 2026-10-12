package oit.is.z3680.kaizi.janken.model;

public class janken {

  private String myHand;
  private String cpuHand;
  private String result;

  public janken(String myHand, String cpuHand, String result) {
    this.myHand = myHand;
    this.cpuHand = cpuHand;
    this.result = result;
  }

  public String getMyHand() {
    return myHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
