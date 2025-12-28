package potatowolfie.earth_and_water.item.custom;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.function.Function;
import net.minecraft.datafixer.TypeReferences;

public class ProjectileItemTypeFix extends DataFix {
    private static final String EMPTY_ID = "minecraft:empty";

    public ProjectileItemTypeFix(Schema outputSchema) {
        super(outputSchema, true);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(TypeReferences.ENTITY);
        Type<?> type2 = this.getOutputSchema().getType(TypeReferences.ENTITY);
        return this.fixTypeEverywhereTyped("Fix AbstractArrow item type", type, type2, composeFixers(this.createFixApplier("minecraft:trident", this::fixTrident), this.createFixApplier("minecraft:arrow", this::fixArrow), this.createFixApplier("minecraft:spectral_arrow", this::fixSpectralArrow)));
    }

    @SafeVarargs
    private static <T> Function<T, T> composeFixers(Function<T, T>... functions) {
        return (input) -> {
            T result = input;
            for (Function<T, T> function : functions) {
                result = function.apply(result);
            }
            return result;
        };
    }

    private Function<Typed<?>, Typed<?>> createFixApplier(String id, FixerFunction fixer) {
        Type<?> inputType = this.getInputSchema().getChoiceType(TypeReferences.ENTITY, id);
        Type<?> outputType = this.getOutputSchema().getChoiceType(TypeReferences.ENTITY, id);
        return createFixApplier(id, fixer, inputType, outputType);
    }

    private static Function<Typed<?>, Typed<?>> createFixApplier(String id, FixerFunction fixer, Type<?> inputType, Type<?> outputType) {
        OpticFinder<?> opticFinder = DSL.namedChoice(id, inputType);
        return (typed) -> {
            return typed.updateTyped(opticFinder, outputType, (typedx) -> {
                return fixer.fix(typedx, outputType);
            });
        };
    }

    @SuppressWarnings("unchecked")
    private <T> Typed<T> fixArrow(Typed<T> typed, Type<?> type) {
        return (Typed<T>) typed.update(DSL.remainderFinder(), (Dynamic<?> data) -> {
            return data.set("item", createStack(data, getArrowId(data)));
        });
    }

    private static String getArrowId(Dynamic<?> arrowData) {
        return arrowData.get("Potion").asString("minecraft:empty").equals("minecraft:empty") ? "minecraft:arrow" : "minecraft:tipped_arrow";
    }

    @SuppressWarnings("unchecked")
    private <T> Typed<T> fixSpectralArrow(Typed<T> typed, Type<?> type) {
        return (Typed<T>) typed.update(DSL.remainderFinder(), (Dynamic<?> data) -> {
            return data.set("item", createStack(data, "minecraft:spectral_arrow"));
        });
    }
    private static Dynamic<?> createStack(Dynamic<?> projectileData, String id) {
        return projectileData.createMap(ImmutableMap.of(projectileData.createString("id"), projectileData.createString(id), projectileData.createString("Count"), projectileData.createInt(1)));
    }

    private <T> Typed<T> fixTrident(Typed<T> typed, Type<?> type) {
        return typed;
    }

    private interface FixerFunction {
        <T> Typed<T> fix(Typed<T> typed, Type<?> type);
    }
}