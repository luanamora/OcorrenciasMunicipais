using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class CidadeMap : IEntityTypeConfiguration<Cidade>
    {
        public void Configure(EntityTypeBuilder<Cidade> entity)
        {
            entity.HasKey(e => e.CdCidade);

            entity.ToTable("cidade");

            entity.Property(e => e.CdCidade).HasColumnName("cd_cidade");

            entity.Property(e => e.CdEstado).HasColumnName("cd_estado");


            entity.Property(e => e.NmCidade)
                .IsRequired()
                .HasColumnName("nm_cidade")
                .HasColumnType("character varying(120)");

            entity.HasOne(d => d.CdEstadoNavigation)
                .WithMany(p => p.Cidade)
                .HasForeignKey(d => d.CdEstado)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("estado_cidade_fk");
        }
    }
}
