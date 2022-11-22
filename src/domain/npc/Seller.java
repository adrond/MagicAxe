package domain.npc;

import domain.npc.Merchant;

public interface Seller {
    String sell(Merchant.Goods goods);
}