package emcapi.bxlib;

import net.minecraftforge.common.config.Config;

@Config(modid = BXLib.MODID)
public class BXConfig {
    @Config.Comment("Configuration dart shooter cd")
    public static final DartShooterConfig DART_SHOOTER_CONFIG = new DartShooterConfig();
    public static class DartShooterConfig {
        @Config.Comment("The interval of next round shoot after finish one round(tick)")
        @Config.RangeInt(min = 0)
        public int INTERVAL = 60 / 2;
        @Config.Comment("The shoot count of one round.")
        @Config.RangeInt(min = 0)
        public int COUNT = 12 * 3;
    }
    @Config.Comment("Configuration laser gun cd")
    public static final LaserConfig LASER_CONFIG = new LaserConfig();
    public static class LaserConfig {
        @Config.Comment("The interval of next round shoot after finish one round(tick)")
        @Config.RangeInt(min = 0)
        public int INTERVAL = 140;
        @Config.Comment("The shoot count of one round.")
        @Config.RangeInt(min = 0)
        public int COUNT = 170;
    }
    @Config.Comment("Configuration Enchantment")
    public static final EnchantmentConfig ENCHANTMENT_CONFIG = new EnchantmentConfig();
    public static class EnchantmentConfig{
        @Config.Comment("Enchantment id banned")
        public String[] BANNED_ID = new String[]{
                "25"
        };
    }
    @Config.Comment("Configuration Traits")
    public static final TraitsConfig TRAITS_CONFIG = new TraitsConfig();
    public static class TraitsConfig {
        @Config.Comment("The leon trait(信素热流)'s maximum interval to reduce damage")
        public int LEON_TIME = 5;
        @Config.Comment("The damage ratio when leon(信素热流) active")
        public float LEON_RATIO = 0.01f;
        @Config.Comment("EMC limit for player")
        public int EMC_LIMIT = 1000;
        @Config.Comment("EMC world max damage ratio")
        public float EMC_UPPER_RATIO = 1.8f;
        @Config.Comment("EMC world min damage ratio")
        public float EMC_LOWER_RATIO = 0.8f;
        @Config.Comment("EMC world armor max emc cost from now emc ratio")
        public float EMC_SHIELD_RATIO = 0.3333333f;
        @Config.Comment("EMC world armor reduce how many damage per EMC")
        public float EMC_SHIELD_REDUCE_RATIO = 0.05f;
        @Config.Comment("Ganbj(血杯) damage increase per health point base on attack.")
        public float GANBJ_RATIO = 1 / 90f;
        @Config.Comment("Ganbj(血杯) min health to increase attack damage.")
        public float GANBJ_LIMIT = 30;
        @Config.Comment("Distance(刺客) min damage ratio.")
        public float DISTANCE_LOWER_RATIO = 0f;
        @Config.Comment("Distance(刺客) max damage ratio.")
        public float DISTANCE_UPPER_RATIO = 1.5f;
        @Config.Comment("Distance(刺客) distance to reach min damage")
        public float DISTANCE_FAR_DISTANCE = 6f;
        @Config.Comment("Distance(刺客) distance to reach max damage")
        public float DISTANCE_NEAR_DISTANCE = 0f;
        @Config.Comment("Distance armor(贴贴/扰动) distance to change resist")
        public float DISTANCE_CHANGE_LIMIT = 2;
        @Config.Comment("Distance armor(贴贴/扰动) resist to near target")
        public float DISTANCE_RESIST_NEAR = 0.75f;
        @Config.Comment("Distance armor(贴贴/扰动) resist to far target")
        public float DISTANCE_RESIST_FAR = 1f;
    }
}
