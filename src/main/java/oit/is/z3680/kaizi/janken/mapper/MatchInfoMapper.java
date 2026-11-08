
package oit.is.z3680.kaizi.janken.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import oit.is.z3680.kaizi.janken.model.MatchInfo;

@Mapper
public interface MatchInfoMapper {

  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchinfo")
  ArrayList<MatchInfo> selectAll();
}
