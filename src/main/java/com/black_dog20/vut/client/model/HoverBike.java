package com.black_dog20.vut.client.model;

import org.lwjgl.opengl.GL11;

import com.black_dog20.vut.entity.EntityVehicle;
import com.black_dog20.vut.utility.VehicleHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Speeder - Black_dog20
 * Created using Tabula 4.1.1
 */
public class HoverBike extends ModelBase {
    public ModelRenderer Long;
    public ModelRenderer Buttom;
    public ModelRenderer Top_fin;
    public ModelRenderer Buttom_fin;
    public ModelRenderer Block;
    public ModelRenderer big_windshield;
    public ModelRenderer front_1;
    public ModelRenderer front_2;
    public ModelRenderer chest_1;
    public ModelRenderer chest_2;
    public ModelRenderer engine_1;
    public ModelRenderer back_fin;
    public ModelRenderer right_fin;
    public ModelRenderer left_fin;
    public ModelRenderer medium_windshield;
    public ModelRenderer small_windshield;
    public ModelRenderer engine_2;

    public HoverBike() {
    	  this.textureWidth = 521;
          this.textureHeight = 512;
          this.big_windshield = new ModelRenderer(this, 200, 200);
          this.big_windshield.setRotationPoint(0.0F, -6.0F, 32.0F);
          this.big_windshield.addBox(0.0F, 0.0F, 0.0F, 9, 6, 1, 0.0F);
          this.engine_1 = new ModelRenderer(this, 70, 400);
          this.engine_1.setRotationPoint(-1.0F, -1.0F, -3.0F);
          this.engine_1.addBox(0.0F, 0.0F, 0.0F, 11, 9, 4, 0.0F);
          this.chest_2 = new ModelRenderer(this, 20, 400);
          this.chest_2.setRotationPoint(-3.0F, 0.0F, 3.0F);
          this.chest_2.addBox(0.0F, 0.0F, 0.0F, 3, 6, 6, 0.0F);
          this.right_fin = new ModelRenderer(this, 280, 300);
          this.right_fin.setRotationPoint(11.0F, 5.0F, 32.0F);
          this.right_fin.addBox(0.0F, 0.0F, 0.0F, 11, 1, 6, 0.0F);
          this.setRotateAngle(right_fin, 0.0F, 0.0F, 0.36425021489121656F);
          this.front_2 = new ModelRenderer(this, 330, 20);
          this.front_2.setRotationPoint(9.0F, 1.0F, 48.0F);
          this.front_2.addBox(0.0F, 0.0F, 0.0F, 1, 10, 2, 0.0F);
          this.small_windshield = new ModelRenderer(this, 200, 240);
          this.small_windshield.setRotationPoint(1.0F, 1.0F, 1.0F);
          this.small_windshield.addBox(0.0F, 0.0F, 0.0F, 5, 4, 1, 0.0F);
          this.back_fin = new ModelRenderer(this, 250, 300);
          this.back_fin.setRotationPoint(4.0F, -6.0F, 2.0F);
          this.back_fin.addBox(0.0F, 0.0F, 0.0F, 1, 6, 8, 0.0F);
          this.Long = new ModelRenderer(this, 50, 50);
          this.Long.setRotationPoint(27.0F, -4.0F, -5.0F);
          this.Long.addBox(0.0F, 0.0F, 0.0F, 9, 7, 50, 0.0F);
          this.setRotateAngle(Long, 0.0F, -1.5707963267948966F, 0.0F);
          this.Buttom = new ModelRenderer(this, 25, 25);
          this.Buttom.setRotationPoint(0.0F, 7.0F, 25.0F);
          this.Buttom.addBox(0.0F, 0.0F, 0.0F, 9, 5, 25, 0.0F);
          this.medium_windshield = new ModelRenderer(this, 200, 220);
          this.medium_windshield.setRotationPoint(1.0F, 1.0F, 1.0F);
          this.medium_windshield.addBox(0.0F, 0.0F, 0.0F, 7, 5, 1, 0.0F);
          this.left_fin = new ModelRenderer(this, 290, 300);
          this.left_fin.setRotationPoint(-12.0F, 9.0F, 33.0F);
          this.left_fin.addBox(0.0F, 0.0F, 0.0F, 11, 1, 6, 0.0F);
          this.setRotateAngle(left_fin, 0.0F, 0.0F, -0.36425021489121656F);
          this.engine_2 = new ModelRenderer(this, 110, 400);
          this.engine_2.setRotationPoint(1.0F, 1.0F, -4.0F);
          this.engine_2.addBox(0.0F, 0.0F, 0.0F, 9, 7, 4, 0.0F);
          this.Block = new ModelRenderer(this, 176, 84);
          this.Block.setRotationPoint(-2.0F, 0.0F, 31.0F);
          this.Block.addBox(0.0F, 0.0F, 0.0F, 13, 12, 18, 0.0F);
          this.front_1 = new ModelRenderer(this, 300, 21);
          this.front_1.setRotationPoint(-1.0F, 1.0F, 48.0F);
          this.front_1.addBox(0.0F, 0.0F, 0.0F, 1, 10, 2, 0.0F);
          this.chest_1 = new ModelRenderer(this, 20, 350);
          this.chest_1.setRotationPoint(9.0F, 0.0F, 3.0F);
          this.chest_1.addBox(0.0F, 0.0F, 0.0F, 3, 6, 6, 0.0F);
          this.Long.addChild(this.big_windshield);
          this.Long.addChild(this.engine_1);
          this.Long.addChild(this.chest_2);
          this.Long.addChild(this.right_fin);
          this.Long.addChild(this.front_2);
          this.medium_windshield.addChild(this.small_windshield);
          this.Long.addChild(this.back_fin);
          this.Long.addChild(this.Buttom);
          this.big_windshield.addChild(this.medium_windshield);
          this.Long.addChild(this.left_fin);
          this.engine_1.addChild(this.engine_2);
          this.Long.addChild(this.Block);
          this.Long.addChild(this.front_1);
          this.Long.addChild(this.chest_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	if(entity instanceof EntityVehicle){
    		EntityVehicle vehicle = (EntityVehicle) entity;
    		if(VehicleHelper.ChestUpgrade(vehicle)==1){
    			this.chest_1.isHidden = false;
    		}
    		else if(VehicleHelper.ChestUpgrade(vehicle)==2){
    			this.chest_1.isHidden = false;
    			this.chest_2.isHidden = false;
    		}
    		else{
       			this.chest_1.isHidden = true;
    			this.chest_2.isHidden = true;
    		}
    	}
    	GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		this.Long.render(f5);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
