
package oit.is.z3680.kaizi.janken.model;

public class Match {
  private long id;
  private long user1;
  private long user2;
  private String user1Hand;
  private String user2Hand;

  public Match() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUser1() {
    return user1;
  }

  public void setUser1(long user1) {
    this.user1 = user1;
  }

  public long getUser2() {
    return user2;
  }

  public void setUser2(long user2) {
    this.user2 = user2;
  }

  public String getUser1Hand() {
    return user1Hand;
  }

  public void setUser1Hand(String user1Hand) {
    this.user1Hand = user1Hand;
  }

  public String getUser2Hand() {
    return user2Hand;
  }

  public void setUser2Hand(String user2Hand) {
    this.user2Hand = user2Hand;
  }
}
