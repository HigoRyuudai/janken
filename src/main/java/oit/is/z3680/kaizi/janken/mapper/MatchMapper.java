
package oit.is.z3680.kaizi.janken.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import oit.is.z3680.kaizi.janken.model.Match;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface MatchMapper {

  @Select("SELECT id, user1, user2, user1Hand, user2Hand FROM matches")
  ArrayList<Match> selectAll();

  @Insert("""
      INSERT INTO matches (user1, user2, user1Hand, user2Hand)
      VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand})
      """)
  int insertMatch(Match match);
}
