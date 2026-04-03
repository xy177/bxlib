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

    @Config.Comment("Configuration for Tinkers' Nunchaku / 匠魂双节棍配置")
    public static final NunchakuConfig NUNCHAKU_CONFIG = new NunchakuConfig();

    public static class NunchakuConfig {

        // ── 基础攻击速度 ────────────────────────────────────────────
        @Config.Comment({
                "Attack speed of the Tinkers' Nunchaku.",
                "Range: 0.1 (very slow) ~ 4.0 (extremely fast). Vanilla sword = 1.6.",
                "匠魂双节棍的攻击速度，原版剑为 1.6，数值越高越快。"
        })
        @Config.RangeDouble(min = 0.1, max = 4.0)
        public double ATTACK_SPEED = 2.5d;

        // ── 连击系统 ────────────────────────────────────────────────

        @Config.Comment({
                "Base combo power cap before binding material modifier.",
                "Actual cap = BASE_COMBO_CAP × (binding.extraDurability / 50) × COMBO_CAP_BINDING_MULTIPLIER",
                "连击能量基础上限（未乘以绑定结系数）。",
                "实际上限 = 此值 × (绑定结extraDurability/50) × COMBO_CAP_BINDING_MULTIPLIER"
        })
        @Config.RangeDouble(min = 0.1, max = 5.0)
        public float BASE_COMBO_CAP = 1.0f;

        @Config.Comment({
                "Multiplier applied on top of the binding material's durability coefficient.",
                "Increase this to allow higher combo caps for better binding materials.",
                "绑定结耐久系数的额外倍率。调大此值可让高级绑定结获得更高的连击上限。"
        })
        @Config.RangeDouble(min = 0.1, max = 10.0)
        public float COMBO_CAP_BINDING_MULTIPLIER = 1.0f;

        @Config.Comment({
                "Combo power gained per successful hit.",
                "每次成功命中积累的连击能量值。"
        })
        @Config.RangeDouble(min = 0.01, max = 1.0)
        public float COMBO_GAIN_PER_HIT = 0.1f;

        @Config.Comment({
                "Ticks of inactivity before combo power starts decaying.",
                "20 ticks = 1 second. Default 60 = 3 seconds.",
                "停止命中多少 tick 后开始衰减连击能量。20 tick = 1 秒，默认 60 = 3 秒。"
        })
        @Config.RangeInt(min = 1)
        public int COMBO_DECAY_DELAY = 60;

        @Config.Comment({
                "Combo power decay amount per tick after the delay.",
                "Higher = faster decay. Default 0.02 means ~50 ticks to fully drain from cap 1.0.",
                "超过延迟后每 tick 衰减的连击能量。值越大衰减越快。",
                "默认 0.02 意味着上限为 1.0 时约需 50 tick（2.5 秒）完全归零。"
        })
        @Config.RangeDouble(min = 0.001, max = 1.0)
        public float COMBO_DECAY_RATE = 0.02f;
    }

}
