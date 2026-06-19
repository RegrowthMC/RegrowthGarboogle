package org.lushplugins.regrowthgarboogle.packet;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.entity.data.EntityData;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.entity.pose.EntityPose;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public class PacketHandler {

    public void frogTongueAnimation(int entityId, Collection<Player> viewers) {
        WrapperPlayServerEntityMetadata packet = new WrapperPlayServerEntityMetadata(
            entityId,
            List.of(new EntityData<>((byte) 6, EntityDataTypes.ENTITY_POSE, EntityPose.USING_TONGUE))
        );

        for (Player viewer : viewers) {
            PacketEvents.getAPI().getPlayerManager().sendPacket(viewer, packet);
        }
    }
}
