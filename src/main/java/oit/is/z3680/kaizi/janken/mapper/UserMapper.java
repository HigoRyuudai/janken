package oit.is.z3680.kaizi.janken.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import oit.is.z3680.kaizi.janken.model.User;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  @Select("SELECT id, name FROM users")
  ArrayList<User> selectAll();

  @Select("SELECT id, name FROM users WHERE id = #{id}")
  User selectById(@Param("id") long id);
}
