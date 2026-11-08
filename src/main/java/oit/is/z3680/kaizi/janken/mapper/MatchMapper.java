
package oit.is.z3680.kaizi.janken.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import oit.is.z3680.kaizi.janken.model.Match;

@Mapper
public interface MatchMapper {

  @Select("SELECT id, user1, user2, user1Hand, user2Hand, isActive FROM matches")
  ArrayList<Match> selectAll();

  @Insert("""
      INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive)
      VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive})
      """)
  int insertMatch(Match match);
}
