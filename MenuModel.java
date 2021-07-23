package com.example.saree360.AdapterClass;

public class MenuModel {

//    public String menuName;
//    public boolean hasChildren, isGroup;
//
//    public MenuModel(String menuName, boolean isGroup, boolean hasChildren) {
//
//        this.menuName = menuName;
//        this.isGroup = isGroup;
//        this.hasChildren = hasChildren;
//    }



        String iconName = "";
        int iconImg = -1; // menu icon resource id

        public String getIconName() {
            return iconName;
        }
        public void setIconName(String iconName) {
            this.iconName = iconName;
        }
        public int getIconImg() {
            return iconImg;
        }
        public void setIconImg(int iconImg) {
            this.iconImg = iconImg;

    }
}