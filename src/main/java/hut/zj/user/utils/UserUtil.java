package hut.zj.user.utils;

import hut.zj.user.entity.User;

public class UserUtil {
    /**
     * 用户更新属性设置
     * @param newUser
     * @param oldUser
     * @return
     */
    public static User updateUser(User newUser, User oldUser){
        oldUser.setCard(newUser.getCard());
        oldUser.setCollege(newUser.getCollege());
        oldUser.setPhone(newUser.getPhone());
        oldUser.setRealName(newUser.getRealName());
        oldUser.setNation(newUser.getNation());
        oldUser.setBirthday(newUser.getBirthday());
        oldUser.setAdministrativeFunction(newUser.getAdministrativeFunction());
        oldUser.setDegree(newUser.getDegree());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setProfessionalFunction(newUser.getProfessionalFunction());
        oldUser.setResume(newUser.getResume());
        oldUser.setSpecialization(newUser.getSpecialization());
        return oldUser;
    }
}
