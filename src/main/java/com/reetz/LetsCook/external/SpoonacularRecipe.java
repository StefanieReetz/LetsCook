package com.reetz.LetsCook.external;

import java.util.List;

public class SpoonacularRecipe {
    private Integer id;
    private String title;
    private String image;
    private Integer usedIngredientCount;
    private Integer missedIngredientCount;
    private List<IngredientInfo> usedIngredients;
    private List<IngredientInfo> missedIngredients;

    public Integer getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(Integer usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(Integer missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public List<IngredientInfo> getUsedIngredients() {
        return usedIngredients;
    }

    public void setUsedIngredients(List<IngredientInfo> usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

    public List<IngredientInfo> getMissedIngredients() {
        return missedIngredients;
    }

    public void setMissedIngredients(List<IngredientInfo> missedIngredients) {
        this.missedIngredients = missedIngredients;
    }

    public static class IngredientInfo {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}