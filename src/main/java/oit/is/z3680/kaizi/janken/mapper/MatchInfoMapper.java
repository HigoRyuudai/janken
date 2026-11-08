package oit.is.z3680.kaizi.janken.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import oit.is.z3680.kaizi.janken.model.MatchInfo;

@Mapper
public interface MatchInfoMapper {

  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchinfo")
  ArrayList<MatchInfo> selectAll();

  @Insert("""
      INSERT INTO matchinfo (user1, user2, user1Hand, isActive)
      VALUES (#{user1}, #{user2}, #{user1Hand}, #{active})
      """)
  int insertMatchInfo(MatchInfo matchInfo);

  @Select("""
      SELECT id, user1, user2, user1Hand, isActive
      FROM matchinfo
      WHERE isActive = TRUE
      AND user2 = #{userId}
      """)
  MatchInfo selectActiveByUserId(@Param("userId") long userId);

  @Update("""
      UPDATE matchinfo
      SET isActive = FALSE
      WHERE id = #{id}
      """)
  int updateActiveFalse(@Param("id") long id);
}
